/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.services;

import com.ashish.notifyapp.api.daos.DepartmentDao;
import com.ashish.notifyapp.api.daos.DepartmentDaoImpl;
import com.ashish.notifyapp.api.exceptions.DataNotFoundException;
import com.ashish.notifyapp.api.exceptions.DataUpdateFailedException;
import com.ashish.notifyapp.api.models.Department;
import java.util.List;

/**
 *
 * @author Ashish
 */
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao dao;

    public DepartmentServiceImpl() {
        this.dao = new DepartmentDaoImpl();
    }
    
    @Override
    public Department getDepartment(long id) {
        Department department = dao.getDepartment(id);
        if (department == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = dao.getAllDepartments();
        if (departments == null || departments.isEmpty()) {
            throw new DataNotFoundException("Resource(s) not found.");
        }
        return departments;
    }

    @Override
    public Department create(Department department) {
        department = dao.create(department);
        if (department == null) {
            throw new DataUpdateFailedException("Resource creation failed.");
        }
        return department;
    }

    @Override
    public Department update(long id, Department department) {
        department = dao.update(id, department);
        if (department == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return department;
    }

    @Override
    public Department delete(long id) {
        Department department = dao.delete(id);
        if (department == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return department;
    }

    @Override
    public boolean delete() {
        boolean status = dao.delete();
        if (!status) {
            throw new DataNotFoundException("Resource(s) not found.");
        }
        return status;
    }
    
}
