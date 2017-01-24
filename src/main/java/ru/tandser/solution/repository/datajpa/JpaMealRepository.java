package ru.tandser.solution.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tandser.solution.domain.Meal;

import java.util.List;

public interface JpaMealRepository extends JpaRepository<Meal, Integer> {
    Meal findOneByIdAndUserId(Integer id, Integer userId);
    List<Meal> findAllByUserId(Integer userId);
}