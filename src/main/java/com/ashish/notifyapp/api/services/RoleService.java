package com.ashish.notifyapp.api.services;

import com.ashish.notifyapp.api.models.Role;
import java.util.List;

public interface RoleService {

	public Role getRole(long id);
	public List<Role> getAllRoles();
	public Role create(Role role);
	public Role update(long id, Role role);
	public Role delete(long id);
	public boolean delete();
}
