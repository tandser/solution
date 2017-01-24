package ru.tandser.solution.repository;

import ru.tandser.solution.domain.User;

import java.util.List;

public interface UserRepository {

    User get(Integer id);

    List<User> getAll();

    User remove(Integer id);

    User put(User user);
}