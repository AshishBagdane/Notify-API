package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.Role;
import java.util.List;

public interface RoleDao {

    public Role getRole(long id);
    public List<Role> getAllRoles();
    public Role create(Role role);
    public Role update(long id, Role role);
    public Role delete(long id);
    public boolean delete();
}
