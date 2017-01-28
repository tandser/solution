package ru.tandser.solution;

import org.springframework.util.ResourceUtils;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.util.Matcher;
import ru.tandser.solution.web.json.JacksonObjectMapper;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Objects;

public class UserTestData {

    public static User admin;
    public static User user;
    public static User newUser;
    public static User duplicateUser;
    public static User invalidNameUser;
    public static User invalidEmailUser;
    public static User invalidPasswordUser;
    public static User invalidNormOfCaloriesUser;

    public static final Matcher<User> USER_MATCHER = new Matcher<>((expected, actual) ->
            expected == actual || (Objects.equals(expected.getName(),           actual.getName())           &&
                                   Objects.equals(expected.getEmail(),          actual.getEmail())          &&
                                   Objects.equals(expected.getPassword(),       actual.getPassword())       &&
                                   Objects.equals(expected.getRole(),           actual.getRole())           &&
                                   Objects.equals(expected.getNormOfCalories(), actual.getNormOfCalories())));

    private UserTestData() {}

    public static void loadMocks() throws Exception {
        BufferedInputStream input = new BufferedInputStream(
                new FileInputStream(ResourceUtils.getFile("classpath:mocks/users.json")));

        Iterator<User> mocks = JacksonObjectMapper.getInstance()
                .readerFor(User.class).<User>readValues(input).readAll().iterator();

        input.close();

        admin                     = mocks.next();
        user                      = mocks.next();
        newUser                   = mocks.next();
        duplicateUser             = mocks.next();
        invalidNameUser           = mocks.next();
        invalidEmailUser          = mocks.next();
        invalidPasswordUser       = mocks.next();
        invalidNormOfCaloriesUser = mocks.next();
    }
}