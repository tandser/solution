package ru.tandser.solution.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;

import java.util.List;

@Service("userService") // удалить после тестирования
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
}