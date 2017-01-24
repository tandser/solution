package ru.tandser.solution.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.User;

import java.util.List;

@Transactional(readOnly = true)
public interface JpaUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    List<User> removeById(Integer id);
}