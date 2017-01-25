package ru.tandser.solution;

import ru.tandser.solution.domain.User;
import ru.tandser.solution.util.Matcher;

import java.util.Objects;

public class UserTestData {

    public static User admin;
    public static User user;
    public static User newUser;
    public static User duplicateUser;
    public static User invalidNameUser;
    public static User invalidEmailUser;
    public static User invalidPasswordUser;
    public static User invalidRoleUser;
    public static User invalidNormOfCaloriesUser;

    public static final Matcher<User> USER_MATCHER = new Matcher<>((expected, actual) ->
            expected == actual || (Objects.equals(expected.getName(),           actual.getName())           &&
                                   Objects.equals(expected.getEmail(),          actual.getEmail())          &&
                                   Objects.equals(expected.getPassword(),       actual.getPassword())       &&
                                   Objects.equals(expected.getRole(),           actual.getRole())           &&
                                   Objects.equals(expected.getNormOfCalories(), actual.getNormOfCalories())));

    static {
        admin = new User();
        admin.setName("Lynn Douglas");
        admin.setEmail("l.douglas@gmail.com");
        admin.setPassword("Mr01LRc");
        admin.setRole(User.Role.ADMIN);
        admin.setNormOfCalories(2000);

        user = new User();
        user.setName("Scott Welch");
        user.setEmail("s.welch@gmail.com");
        user.setPassword("Izhyw29");
        user.setRole(User.Role.USER);
        user.setNormOfCalories(2000);

        newUser = new User();
        newUser.setName("Ralph Bass");
        newUser.setEmail("r.bass@gmail.com");
        newUser.setPassword("9Mn5Z6x");
        newUser.setRole(User.Role.USER);
        newUser.setNormOfCalories(2000);

        duplicateUser = new User();
        duplicateUser.setName("Steven Welch");
        duplicateUser.setEmail("s.welch@gmail.com");
        duplicateUser.setPassword("Y9rpqov");
        duplicateUser.setRole(User.Role.USER);
        duplicateUser.setNormOfCalories(2000);

        invalidNameUser = new User();
        invalidNameUser.setName("");
        invalidNameUser.setEmail("l.jones@gmail.com");
        invalidNameUser.setPassword("uGbn0oU");
        invalidNameUser.setRole(User.Role.USER);
        invalidNameUser.setNormOfCalories(2000);

        invalidEmailUser = new User();
        invalidEmailUser.setName("Cary Rhodes");
        invalidEmailUser.setEmail("c.rhodesgmail.com");
        invalidEmailUser.setPassword("lgZtBlx");
        invalidEmailUser.setRole(User.Role.USER);
        invalidEmailUser.setNormOfCalories(2000);

        invalidPasswordUser = new User();
        invalidPasswordUser.setName("Nathaniel Perez");
        invalidPasswordUser.setEmail("n.perez@gmail.com");
        invalidPasswordUser.setPassword("SyDDH");
        invalidPasswordUser.setRole(User.Role.USER);
        invalidPasswordUser.setNormOfCalories(2000);

        invalidRoleUser = new User();
        invalidRoleUser.setName("Bruce Cummings");
        invalidRoleUser.setEmail("b.cummings@gmail.com");
        invalidRoleUser.setPassword("ilYZkRs");
        invalidRoleUser.setRole(null);
        invalidRoleUser.setNormOfCalories(2000);

        invalidNormOfCaloriesUser = new User();
        invalidNormOfCaloriesUser.setName("Trevor Weaver");
        invalidNormOfCaloriesUser.setEmail("t.weaver@gmail.com");
        invalidNormOfCaloriesUser.setPassword("aE8hC4Y");
        invalidNormOfCaloriesUser.setRole(User.Role.USER);
        invalidNormOfCaloriesUser.setNormOfCalories(-1);
    }
}