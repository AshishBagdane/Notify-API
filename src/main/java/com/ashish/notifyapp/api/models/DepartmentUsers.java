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
@Table(name = "DEPARTMENT_PROFILES")
public class DepartmentUsers implements Serializable {

    @Column(name = "DEPARTMENTID", columnDefinition = "REFERENCES DEPARTMENT ON DELETE CASCADE")
    private long departmentId;
    @Column(name = "USERNAME", length = 50, columnDefinition = "REFERENCES PROFILE ON DELETE CASCADE")
    private String username;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public DepartmentUsers() {
    }

    public DepartmentUsers(long departmentId, String username) {
        this.departmentId = departmentId;
        this.username = username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
