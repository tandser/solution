package ru.tandser.solution.service;

import ru.tandser.solution.domain.User;

import java.util.List;

public interface UserService {

    User get(int id);

    List<User> getAll();

    User getByEmail(String email);

    User getWithMeals(int id);

    void remove(int id);

    User save(User user);

    void update(User user);
}