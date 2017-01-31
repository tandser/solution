package ru.tandser.solution;

import org.springframework.util.ResourceUtils;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.util.Matcher;
import ru.tandser.solution.web.json.JsonConverter;

import java.util.Iterator;
import java.util.Objects;

public class UserTestData {

    public static User admin;
    public static User user;
    public static User newUser;
    public static User notNewUser;
    public static User nonExistentUser;
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
        Iterator<User> mocks = JsonConverter.fromJsonToList(ResourceUtils.getFile("classpath:mocks/users.json"), User.class).iterator();

        admin                     = mocks.next();
        user                      = mocks.next();
        newUser                   = mocks.next();
        notNewUser                = mocks.next();
        nonExistentUser           = mocks.next();
        duplicateUser             = mocks.next();
        invalidNameUser           = mocks.next();
        invalidEmailUser          = mocks.next();
        invalidPasswordUser       = mocks.next();
        invalidNormOfCaloriesUser = mocks.next();
    }
}