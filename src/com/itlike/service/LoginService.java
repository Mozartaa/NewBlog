package com.itlike.service;

import com.itlike.domain.User;

import java.util.List;

public interface LoginService {
    public User login(User user);

    void save(User user);

    List<User> getAllUsers();

    void delete(User user);


}
