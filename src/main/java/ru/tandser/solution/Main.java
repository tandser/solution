package ru.tandser.solution;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/datajpa-config.xml");
        ctx.refresh();

//        UserRepository userService = ctx.getBean("userService", UserRepository.class);
//        MealRepository mealService = ctx.getBean("mealService", MealRepository.class);
    }
}