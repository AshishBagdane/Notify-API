/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.Post;
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
public class PostDaoImpl implements PostDao {

    @Override
    public Post getPost(long departmentId, String username, long postId) {
        String sql = "SELECT P.* FROM POST P, POSTS_FROM PF WHERE P.ID = PF.POSTID AND PF.DEPARTMENTID = ? AND PF.USERNAME = ? AND PF.POSTID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Post post = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(Post.class);
            query.setLong(0, departmentId);
            query.setString(1, username);
            query.setLong(2, postId);
            List<Post> posts = query.list();
            if (!posts.isEmpty()) {
                post = posts.get(0);
            }
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return post;
    }

    @Override
    public List<Post> getAllPosts(long departmentId, String username) {
        String sql = "SELECT P.* FROM POST P, POSTS_FROM PF WHERE P.ID = PF.POSTID AND PF.DEPARTMENTID = ? AND PF.USERNAME = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Post> posts = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(Post.class);
            query.setLong(0, departmentId);
            query.setString(1, username);
            posts = query.list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return posts;
    }

    @Override
    public Post create(long departmentId, String username, Post post) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return post;
    }

    @Override
    public Post update(long id, Post post) {
        String sql = "UPDATE POST SET TITLE = ?, BODY = ?, IMGURL = ? WHERE ID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Post updatedPost = null;
        if (getPost(id) != null) {
            try {
                transaction = session.beginTransaction();
                Query query = session.createSQLQuery(sql);
                if (query.executeUpdate() > 0) {
                    updatedPost = post;
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
        return updatedPost;
    }

    @Override
    public Post delete(long id) {
        String sql = "DELETE FROM POST WHERE ID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Post deletedPost = getPost(id);
        if (deletedPost != null) {
            try {
                transaction = session.beginTransaction();
                Query query = session.createSQLQuery(sql);
                query.setLong(0, id);
                if (query.executeUpdate() <= 0) {
                    deletedPost = null;
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
        return deletedPost;
    }

    @Override
    public boolean delete(long departmentId, String username) {
        String sql = "DELETE FROM POST WHERE ID IN (SELECT POSTID FROM POSTS_FROM WHERE DEPARTMENTID = ? AND USERNAME = ?)";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.setLong(0, departmentId);
            query.setString(1, username);
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
    
    private Post getPost(long postId) {
        String sql = "SELECT * FROM POST WHERE ID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Post post = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(Post.class);
            query.setLong(0, postId);
            List<Post> posts = query.list();
            if (!posts.isEmpty()) {
                post = posts.get(0);
            }
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return post;
    }
}
