package ru.tandser.solution.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;
import ru.tandser.solution.repository.exc.ConflictException;

import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    private JpaUserRepository userRepository;

    @Autowired
    public void setUserRepository(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
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
    @Transactional
    public User put(User user) {
        if (!user.isNew()) {
            User last = get(user.getId());
            if (last == null) {
                return null;
            } else if (user.getVersion() != last.getVersion()) {
                throw new ConflictException();
            }
        }

        return userRepository.save(user);
    }
}