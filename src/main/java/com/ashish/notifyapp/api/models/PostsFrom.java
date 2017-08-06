/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ashish
 */
@Entity
@Table(name = "POSTS_FROM")
public class PostsFrom implements Serializable {
    
    @Column(name = "POSTID", columnDefinition = "REFERENCES POST ON DELETE CASCADE")
    private long postId;
    @Column(name = "DEPARTMENTID", columnDefinition = "REFERENCES DEPARTMENT ON DELETE CASCADE")
    private long departmentId;
    @Column(name = "USERNAME", length = 50, columnDefinition = "REFERENCES PROFILE ON DELETE CASCADE")
    private String username;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public PostsFrom() {
    }

    public PostsFrom(long postId, long departmentId, String username) {
        this.postId = postId;
        this.departmentId = departmentId;
        this.username = username;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUserId(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
