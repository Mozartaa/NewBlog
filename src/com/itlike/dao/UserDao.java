package com.itlike.dao;

import com.itlike.domain.User;

import java.util.List;

public interface UserDao {
    public User getUser(String username, String password);

    void save(User user);

    List<User> getAllUsers();

    void delete(User user);
}
