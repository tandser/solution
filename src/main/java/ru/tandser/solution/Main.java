package ru.tandser.solution;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.MealRepository;
import ru.tandser.solution.repository.UserRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/datajpa-config.xml");
        ctx.refresh();

        UserRepository userService = ctx.getBean("userService", UserRepository.class);
        MealRepository mealService = ctx.getBean("mealService", MealRepository.class);

        List<User> users = userService.getAll();
        List<Meal> meals = mealService.getAll(2);

        users.forEach(System.out::println);
        meals.forEach(System.out::println);
    }
}