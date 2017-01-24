package ru.tandser.solution.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tandser.solution.domain.User;

public interface JpaUserRepository extends JpaRepository<User, Integer> {

}