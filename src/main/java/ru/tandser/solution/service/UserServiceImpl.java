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

    public static final String MESSAGE_1 = "User with id = %d does not exist";
    public static final String MESSAGE_2 = "User with email = %s does not exist";
    public static final String MESSAGE_3 = "User id = %d, must be null";
    public static final String MESSAGE_4 = "User id must not be null";

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(int id) {
        return requireExist(userRepository.get(id), format(MESSAGE_1, id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email);
        return requireExist(userRepository.getByEmail(email), format(MESSAGE_2, email));
    }

    @Override
    public User getWithMeals(int id) {
        return requireExist(userRepository.getWithMeals(id), format(MESSAGE_1, id));
    }

    @Override
    public void remove(int id) {
        requireExist(userRepository.remove(id), format(MESSAGE_1, id));
    }

    @Override
    public User save(User user) {
        Assert.notNull(user);
        requireNew(user, format(MESSAGE_3, user.getId()));
        return userRepository.put(user);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user);
        requireNotNew(user, MESSAGE_4);
        requireExist(userRepository.put(user), format(MESSAGE_1, user.getId()));
    }
}