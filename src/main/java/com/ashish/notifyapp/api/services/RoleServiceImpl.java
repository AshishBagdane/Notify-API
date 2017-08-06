/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.services;

import com.ashish.notifyapp.api.daos.RoleDao;
import com.ashish.notifyapp.api.daos.RoleDaoImpl;
import com.ashish.notifyapp.api.exceptions.DataNotFoundException;
import com.ashish.notifyapp.api.exceptions.DataUpdateFailedException;
import com.ashish.notifyapp.api.models.Role;
import java.util.List;

/**
 *
 * @author Ashish
 */
public class RoleServiceImpl implements RoleService {

    private final RoleDao dao;

    public RoleServiceImpl() {
        this.dao = new RoleDaoImpl();
    }
    
    @Override
    public Role getRole(long id) {
        Role role = dao.getRole(id);
        if (role == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = dao.getAllRoles();
        if (roles == null || roles.isEmpty()) {
            throw new DataNotFoundException("Resource(s) not found.");
        }
        return roles;
    }

    @Override
    public Role create(Role role) {
        role = dao.create(role);
        if (role == null) {
            throw new DataUpdateFailedException("Resource creation failed.");
        }
        return role;
    }

    @Override
    public Role update(long id, Role role) {
        role = dao.update(id, role);
        if (role == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return role;
    }

    @Override
    public Role delete(long id) {
        Role role = dao.delete(id);
        if (role == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return role;
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
