package com.scm.SmartContactManager.service;

import java.util.*;

import com.scm.SmartContactManager.entities.User;

public interface IUserService {

    public User saveUser(User user);
    public Optional<User> findByEmail(String email);
    public User getUserByEmail(String email);
    public Optional<User> getUser(String userId);
    public List<User> getAllUsers();
    public Optional<User> updateUser(User user);
    public void deleteUser(String userId);
    public void deleteUser(User user);
}