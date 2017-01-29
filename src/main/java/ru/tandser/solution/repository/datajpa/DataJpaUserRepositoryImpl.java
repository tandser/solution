package ru.tandser.solution.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;

import java.time.LocalDateTime;
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
    public User get(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User getWithMeals(int id) {
        return userRepository.findOneWithMeals(id);
    }

    @Override
    public User remove(int id) {
        List<User> result = userRepository.removeById(id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public User put(User user) {
        if (user.getCreated()        == null) user.setCreated(LocalDateTime.now());
        if (user.getRole()           == null) user.setRole(User.Role.USER);
        if (user.getNormOfCalories() == null) user.setNormOfCalories(defaultNormOfCalories);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return user.getId() != null && userRepository.exists(user.getId())
                ? put(user)
                : null;
    }
}