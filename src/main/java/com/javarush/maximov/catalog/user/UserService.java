package com.javarush.maximov.catalog.user;

import java.util.List;

public interface UserService {
    List<User> findByParams(User user);
}
