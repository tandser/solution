package ru.tandser.solution.repository;

import ru.tandser.solution.domain.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public MealRepositoryImpl() {
        Random rand = new Random(); int id = counter.get();

        repository.put(0, new ConcurrentHashMap<>());

        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1, 18, 0), "Supper", rand.nextInt(1000)));

        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2, 18, 0), "Supper", rand.nextInt(1000)));

        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(0).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3, 18, 0), "Supper", rand.nextInt(1000)));

        repository.put(1, new ConcurrentHashMap<>());

        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1, 18, 0), "Supper", rand.nextInt(1000)));

        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2, 18, 0), "Supper", rand.nextInt(1000)));

        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(1).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3, 18, 0), "Supper", rand.nextInt(1000)));

        repository.put(2, new ConcurrentHashMap<>());

        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 1, 18, 0), "Supper", rand.nextInt(1000)));

        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 2, 18, 0), "Supper", rand.nextInt(1000)));

        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3,  8, 0), "Breakfast", rand.nextInt(1000)));
        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3, 12, 0), "Dinner", rand.nextInt(2000)));
        repository.get(2).put(counter.get(), new Meal(counter.getAndIncrement(), LocalDateTime.of(2016, Month.DECEMBER, 3, 18, 0), "Supper", rand.nextInt(1000)));
    }

    @Override
    public void put(int userId, Meal meal) {
        Map<Integer, Meal> meals = repository.get(userId);

        if (meals == null) {
            return;
        }

        if (meal.isNew()) {
            meal.setId(counter.getAndIncrement());
        }

        meals.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> filter(int userId, Predicate<Meal> predicate) {
        Map<Integer, Meal> meals = repository.get(userId);

        if (meals == null) {
            return Collections.emptyList();
        }

        return meals.values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}