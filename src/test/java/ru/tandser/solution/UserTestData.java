package ru.tandser.solution;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLContext;
import org.springframework.util.ResourceUtils;
import ru.tandser.solution.domain.Role;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.util.Matcher;
import ru.tandser.solution.util.Users;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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

    public static void loadMocks() throws Exception {
        FileReader reader = new FileReader(ResourceUtils.getFile("classpath:mocks/users.xml"));

        Mapping mapping = new Mapping();
        mapping.loadMapping(ResourceUtils.getURL("classpath:castor/mapping.xml"));

        XMLContext xmlContext = new XMLContext();
        xmlContext.addMapping(mapping);

        Unmarshaller unmarshaller = xmlContext.createUnmarshaller();
        unmarshaller.setClass(Users.class);

        Users users = (Users) unmarshaller.unmarshal(reader);

        reader.close();

        admin                     = users.getUsers().get(0);
        user                      = users.getUsers().get(1);
        newUser                   = users.getUsers().get(2);
        duplicateUser             = users.getUsers().get(3);
        invalidNameUser           = users.getUsers().get(4);
        invalidEmailUser          = users.getUsers().get(5);
        invalidPasswordUser       = users.getUsers().get(6);
        invalidNormOfCaloriesUser = users.getUsers().get(7);
    }
}