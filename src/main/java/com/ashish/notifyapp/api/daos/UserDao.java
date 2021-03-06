package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.User;
import java.util.List;

public interface UserDao {

    public User getUser(long departmentId, String username);
    public List<User> getAllUsers(long departmentId);
    public User create(long departmentId, User user);
    public User update(long departmentId, String username, User user);
    public User delete(long departmentId, String username);
    public boolean delete(long departmentId);
}
