package ru.tandser.solution.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    private JpaUserRepository userRepository;
    private Integer           defaultNormOfCalories;

    @Autowired
    public void setUserRepository(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${default.normOfCalories}")
    public void setDefaultNormOfCalories(Integer defaultNormOfCalories) {
        this.defaultNormOfCalories = defaultNormOfCalories;
    }

    @Override
    public User get(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User remove(Integer id) {
        List<User> result = userRepository.removeById(id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public User put(User user) {
        if (user.getNormOfCalories() == null) {
            user.setNormOfCalories(defaultNormOfCalories);
        }

        return userRepository.save(user);
    }
}