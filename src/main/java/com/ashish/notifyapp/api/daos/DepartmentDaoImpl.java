/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.Department;
import com.ashish.notifyapp.api.models.User;
import com.ashish.notifyapp.api.utilities.DatabaseConnection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ashish
 */
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public Department getDepartment(long id) {
        String hql = "FROM Department WHERE ID = :deptId";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Department department = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setLong("deptId", id);
            List<Department> departments = query.list();
            if (!departments.isEmpty()) {
                department = departments.get(0);
            }
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        String hql = "FROM Department";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Department> departments = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            departments = query.list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return departments;
    }

    @Override
    public Department create(Department department) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return department;
    }

    @Override
    public Department update(long id, Department department) {
        String hql = "UPDATE Department SET NAME = ? WHERE ID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Department updatedDepartment = null;
        if (getDepartment(id) != null) {
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setString(0, department.getName());
                query.setLong(1, id);
                if (query.executeUpdate() > 0) {
                    updatedDepartment = department;
                }
                transaction.commit();
                session.close();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
        return updatedDepartment;
    }

    @Override
    public Department delete(long id) {
        String hql = "DELETE FROM Department WHERE ID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Department deletedDepartment = getDepartment(id);
        if (deletedDepartment != null) {
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setLong(0, id);
                if (query.executeUpdate() <= 0) {
                    deletedDepartment = null;
                }
                transaction.commit();
                session.close();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
        return deletedDepartment;
    }

    @Override
    public boolean delete() {
        String hql = "DELETE FROM Department";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            status = (query.executeUpdate() > 0);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return status;
    }
    
}
