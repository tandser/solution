package ru.tandser.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;

import java.util.List;

import static java.lang.String.format;
import static ru.tandser.solution.service.util.Inspector.*;

@Service
public class UserServiceImpl implements UserService {

    private static final String MESSAGE_1 = "User with id = %d not found";
    private static final String MESSAGE_2 = "User with email = %s not found";
    private static final String MESSAGE_3 = "User id = %d, must be null";
    private static final String MESSAGE_4 = "User id must not be null";

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(int id) {
        return checkThatFound(userRepository.get(id), format(MESSAGE_1, id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email);
        return checkThatFound(userRepository.getByEmail(email), format(MESSAGE_2, email));
    }

    @Override
    public User getWithMeals(int id) {
        return checkThatFound(userRepository.getWithMeals(id), format(MESSAGE_1, id));
    }

    @Override
    public void remove(int id) {
        checkThatFound(userRepository.remove(id), format(MESSAGE_1, id));
    }

    @Override
    public User save(User user) {
        Assert.notNull(user);
        checkThatNew(user, format(MESSAGE_3, user.getId()));
        return userRepository.put(user);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user);
        checkThatNotNew(user, MESSAGE_4);
        checkThatFound(userRepository.put(user), format(MESSAGE_1, user.getId()));
    }
}