/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.services;

import com.ashish.notifyapp.api.daos.DepartmentUsersDaoImpl;
import com.ashish.notifyapp.api.daos.UserDao;
import com.ashish.notifyapp.api.daos.UserDaoImpl;
import com.ashish.notifyapp.api.exceptions.DataNotFoundException;
import com.ashish.notifyapp.api.exceptions.DataUpdateFailedException;
import com.ashish.notifyapp.api.models.DepartmentUsers;
import com.ashish.notifyapp.api.models.User;
import java.util.List;

/**
 *
 * @author Ashish
 */
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    public UserServiceImpl() {
        this.dao = new UserDaoImpl();
    }
    
    @Override
    public User getUser(long departmentId, String username) {
        User user = dao.getUser(departmentId, username);
        if (user == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers(long departmentId) {
        List<User> users = dao.getAllUsers(departmentId);
        if (users == null || users.isEmpty()) {
            throw new DataNotFoundException("Resource(s) not found.");
        }
        return users;
    }

    @Override
    public User create(long departmentId, User user) {
        user = dao.create(departmentId, user);
        if (user == null) {
            throw new DataUpdateFailedException("Resource creation failed.");
        } else {
            DepartmentUsers departmentUsers = new DepartmentUsers(departmentId, user.getUsername());
            departmentUsers = new DepartmentUsersDaoImpl().create(departmentUsers);
            if (departmentUsers == null) {
                dao.delete(departmentId, user.getUsername());
                user = null;
                throw new DataUpdateFailedException("Sub-resource creation failed.");
            }
        }
        return user;
    }

    @Override
    public User update(long departmentId, String username, User user) {
        user = dao.update(departmentId, username, user);
        if (user == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return user;
    }

    @Override
    public User delete(long departmentId, String username) {
        User user = dao.delete(departmentId, username);
        if (user == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return user;
    }

    @Override
    public boolean delete(long departmentId) {
        boolean status = dao.delete(departmentId);
        if (!status) {
            throw new DataNotFoundException("Resource(s) not found.");
        }
        return status;
    }
    
}
