package ru.tandser.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;

import java.util.List;

import static ru.tandser.solution.service.util.Inspector.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(int id) {
        return requireExist(userRepository.get(id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getByEmail(String email) {
        requireNotNull(email);
        return requireExist(userRepository.getByEmail(email));
    }

    @Override
    public User getWithMeals(int id) {
        return requireExist(userRepository.getWithMeals(id));
    }

    @Override
    public void remove(int id) {
        requireExist(userRepository.remove(id));
    }

    @Override
    public User save(User user) {
        requireNotNull(user);
        requireNew(user);
        return userRepository.put(user);
    }

    @Override
    public void update(User user) {
        requireNotNull(user);
        requireNotNew(user);
        requireExist(userRepository.put(user));
    }
}