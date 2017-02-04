package ru.tandser.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;
import ru.tandser.solution.web.Principal;

import java.util.List;

import static ru.tandser.solution.util.Inspector.requireExist;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository  userRepository;
    private PasswordEncoder passwordEncoder;
    private int             defaultNormOfCalories;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${default.normOfCalories}")
    public void setDefaultNormOfCalories(int defaultNormOfCalories) {
        this.defaultNormOfCalories = defaultNormOfCalories;
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
        user.prepare(passwordEncoder, defaultNormOfCalories);
        return userRepository.put(user);
    }

    @Override
    public void update(User user) {
        user.prepare(passwordEncoder, defaultNormOfCalories);
        requireExist(userRepository.put(user));
    }

    @Override
    @Transactional
    public void toggle(int id, boolean state) {
        User user = get(id);
        user.setEnabled(state);
        userRepository.put(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new Principal(user);
    }
}