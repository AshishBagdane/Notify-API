/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.Post;
import com.ashish.notifyapp.api.models.PostsFrom;
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
public class PostsFromDaoImpl implements PostsFromDao {

    @Override
    public List<Post> getPostsFrom(long departmentId) {
        String sql = "SELECT P.* FROM POST P, POSTS_FROM PF WHERE P.ID = PF.POSTID AND PF.DEPARTMENTID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Post> posts = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(Post.class);
            query.setLong(0, departmentId);
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
    public boolean create(PostsFrom postsFrom) {
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            session.save(postsFrom);
            status = true;
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

    @Override
    public Post getPostFrom(long departmentId, long postId) {
        String sql = "SELECT P.* FROM POST P, POSTS_FROM PF WHERE P.ID = PF.POSTID AND PF.DEPARTMENTID = ? AND PF.POSTID = ?";
        Session session = DatabaseConnection.getSessionFactory().openSession();
        Transaction transaction = null;
        Post post = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(Post.class);
            query.setLong(0, departmentId);
            query.setLong(1, postId);
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
