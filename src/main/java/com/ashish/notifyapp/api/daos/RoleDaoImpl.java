/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.Role;
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
public class RoleDaoImpl implements RoleDao {

    @Override
    public Role getRole(long id) {
        String sql = "SELECT * FROM ROLE WHERE ID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Role role = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.setLong(0, id);
            List<Role> roles = query.list();
            if (roles.isEmpty()) {
                role = roles.get(0);
            }
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        String sql = "SELECT * FROM ROLE";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Role> roles = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            roles = query.list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return roles;
    }

    @Override
    public Role create(Role role) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return role;
    }

    @Override
    public Role update(long id, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role delete(long id) {
        String sql = "DELETE FROM ROLE WHERE ID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Role deletedRole = getRole(id);
        if (deletedRole != null) {
            try {
                transaction = session.beginTransaction();
                Query query = session.createSQLQuery(sql);
                query.setLong(0, id);
                if (query.executeUpdate() <= 0) {
                    deletedRole = null;
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
        return deletedRole;
    }

    @Override
    public boolean delete() {
        String sql = "DELETE FROM ROLE";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
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
