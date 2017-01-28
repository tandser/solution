package ru.tandser.solution.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.User;

import javax.persistence.NamedEntityGraph;
import java.util.List;

@Transactional(readOnly = true)
public interface JpaUserRepository extends JpaRepository<User, Integer> {

    User findOneByEmail(String email);

    @EntityGraph(User.WITH_MEALS)
    @Query("SELECT u FROM User AS u WHERE u.id = ?1")
    User findOneWithMeals(Integer id);

    @Transactional
    List<User> removeById(Integer id);
}