package com.javarush.maximov.catalog.user;

import com.javarush.maximov.catalog.user.User;

import java.util.List;

public interface UserService {
    List<User> findByParams(User user);
}
