package ru.tandser.solution.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface JpaMealRepository extends JpaRepository<Meal, Integer> {

    Meal findOneByIdAndUserId(Integer id, Integer userId);

    List<Meal> findAllByUserId(Integer userId);

    List<Meal> findByUserIdAndDateTimeBetween(Integer userId, LocalDateTime from, LocalDateTime to);

    @Transactional
    List<Meal> removeByIdAndUserId(Integer id, Integer userId);
}