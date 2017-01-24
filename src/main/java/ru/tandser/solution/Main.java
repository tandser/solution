package ru.tandser.solution;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.tandser.solution.repository.MealRepository;
import ru.tandser.solution.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/datajpa-config.xml");
        ctx.refresh();

        UserRepository userService = ctx.getBean("userService", UserRepository.class);
        MealRepository mealService = ctx.getBean("mealService", MealRepository.class);
    }
}