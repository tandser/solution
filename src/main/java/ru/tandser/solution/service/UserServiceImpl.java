package ru.tandser.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;
import ru.tandser.solution.util.Inspector;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(int id) {
        return Inspector.checkFound(userRepository.get(id), id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User remove(int id) {
        return Inspector.checkFound(userRepository.remove(id), id);
    }

    @Override
    public User put(User user) {
        Assert.notNull(user);
        return userRepository.put(user);
    }
}