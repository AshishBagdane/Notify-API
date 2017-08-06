/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.User;
import com.ashish.notifyapp.api.utilities.DatabaseConnection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ashish
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(long departmentId, String username) {
        String sql = "SELECT P.* FROM PROFILE P, Department_PROFILES DU WHERE DU.USERNAME = ? AND DU.DEPARTMENTID = ? AND P.USERNAME = DU.USERNAME";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity("PROFILE", User.class);
            query.setString(0, username);
            query.setLong(1, departmentId);
            List<User> users = query.list();
            if (!users.isEmpty()) {
                user = users.get(0);
            }
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return user;
    }

    @Override
    public List<User> getAllUsers(long departmentId) {
        String sql = "SELECT P.* FROM PROFILE P, DEPARTMENT_PROFILES DP WHERE DP.DEPARTMENTID = ? AND P.USERNAME = DP.USERNAME";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity("PROFILE", User.class);
            query.setLong(0, departmentId);
            users = query.list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return users;
    }

    @Override
    public User create(long departmentId, User user) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return user;
    }

    @Override
    public User update(long departmentId, String username, User user) {
        String hql = "UPDATE User SET FNAME = ?, LNAME = ?, EMAIL = ?, PASSWORD = ? where USERNAME = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        User updatedUser = null;
        if (getUser(departmentId, username) != null) {
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setString(0, user.getFirstName());
                query.setString(1, user.getLastName());
                query.setString(2, user.geteMail());
                query.setString(3, user.getPassword());
                query.setString(4, username);
                if (query.executeUpdate() > 0) {
                    updatedUser = user;
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
        return updatedUser;
    }

    @Override
    public User delete(long departmentId, String username) {
        String hql = "DELETE FROM User WHERE USERNAME = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        User deletedUser = getUser(departmentId, username);
        if (deletedUser != null) {
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setString(0, username);
                if (query.executeUpdate() <= 0) {
                    deletedUser = null;
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
        return deletedUser;
    }

    @Override
    public boolean delete(long departmentId) {
        String sql = "DELETE FROM PROFILE WHERE USERNAME IN (SELECT USERNAME FROM DEPARTMENT_PROFILES WHERE DEPARTMENTID = ?)";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.setLong(0, departmentId);
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
