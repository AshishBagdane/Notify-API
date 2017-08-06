/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.DepartmentUsers;
import com.ashish.notifyapp.api.utilities.DatabaseConnection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ashish
 */
public class DepartmentUsersDaoImpl implements DepartmentUsersDao {

    @Override
    public DepartmentUsers create(DepartmentUsers departmentUsers) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(departmentUsers);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return departmentUsers;
    }
    
}
