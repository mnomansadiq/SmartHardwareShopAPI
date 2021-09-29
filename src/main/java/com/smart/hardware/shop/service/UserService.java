package com.smart.hardware.shop.service;

import com.smart.hardware.shop.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);

    User save(User user);
}
