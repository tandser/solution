package ru.tandser.solution;

import ru.tandser.solution.domain.User;
import ru.tandser.solution.util.Matcher;

import java.util.Objects;

public class UserTestData {

    public static final User USER;

    public static final Matcher<User> USER_MATCHER = new Matcher<>((expected, actual) ->
            expected == actual || (Objects.equals(expected.getName(),           actual.getName())           &&
                                   Objects.equals(expected.getEmail(),          actual.getEmail())          &&
                                   Objects.equals(expected.getPassword(),       actual.getPassword())       &&
                                   Objects.equals(expected.getRole(),           actual.getRole())           &&
                                   Objects.equals(expected.getNormOfCalories(), actual.getNormOfCalories())));

    static {
        USER = new User();
        USER.setName("Lynn Douglas");
        USER.setEmail("l.douglas@gmail.com");
        USER.setPassword("Mr01LRc");
        USER.setRole(User.Role.USER);
        USER.setNormOfCalories(2000);
    }
}