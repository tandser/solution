package ru.tandser.solution.service;

import ru.tandser.solution.domain.User;

import java.util.List;

public interface UserService {

    User get(int id);

    List<User> getAll();

    User remove(int id);

    User put(User user);
}