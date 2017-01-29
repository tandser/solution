package ru.tandser.solution.repository;

import ru.tandser.solution.domain.User;

import java.util.List;

public interface UserRepository {

    User get(int id);

    List<User> getAll();

    User getByEmail(String email);

    User getWithMeals(int id);

    User remove(int id);

    User put(User user);
}