package com.myproject.forum.service;

import com.myproject.forum.models.User;

import java.util.List;

public interface UsersService {
    User createNewUser();
    boolean saveUser(User newUser);
    User getUserByEmail(String userEmail);
    User getUserById(long userId);
    void deleteUser(long userId, String userEmailLogged);
    void updateUser(User newUser);
    List<User> getAllUsers();
    void exchangeUserForAdmin(long userId, String adminEmail);
}
