package ru.tandser.solution.repository;

import ru.tandser.solution.domain.User;

import java.util.List;

public interface UserRepository {
    User get(int id);
    List<User> getAll();
}