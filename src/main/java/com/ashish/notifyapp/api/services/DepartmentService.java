package com.ashish.notifyapp.api.services;

import com.ashish.notifyapp.api.models.Department;
import java.util.List;

public interface DepartmentService {

	public Department getDepartment(long id);
	public List<Department> getAllDepartments();
	public Department create(Department department);
	public Department update(long id, Department department);
	public Department delete(long id);
	public boolean delete();
}
