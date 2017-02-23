package ru.tandser.solution.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface JpaMealRepository extends JpaRepository<Meal, Integer> {

    Meal findOneByIdAndUserId(int id, int userId);

    List<Meal> findAllByUserId(int userId);

    List<Meal> findByUserIdAndDateTimeBetween(int userId, LocalDateTime from, LocalDateTime to);

    @Transactional
    List<Meal> removeByIdAndUserId(int id, int userId);
}