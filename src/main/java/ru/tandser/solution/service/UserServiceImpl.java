package ru.tandser.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.repository.UserRepository;
import ru.tandser.solution.web.Principal;

import java.util.List;

import static ru.tandser.solution.service.util.Inspector.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository  userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.put(user);
    }

    @Override
    public void update(User user) {
        requireNotNull(user);
        requireNotNew(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        requireExist(userRepository.put(user));
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