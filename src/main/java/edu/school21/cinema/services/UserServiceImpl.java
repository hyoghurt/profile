package edu.school21.cinema.services;

import edu.school21.cinema.models.*;
import edu.school21.cinema.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SignInUserService signInUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int signUp(User user) {
        try {
            return userRepository.save(user);
        } catch (DuplicateKeyException e) {
            log.info(e.getMessage());
            return 0;
        }
    }

    @Override
    @Transactional
    public User signIn(String email, String password, String ip, Timestamp date) {
        User findUser = userRepository.findByEmail(email);
        if (findUser != null && passwordEncoder.matches(password, findUser.getPassword())) {
            signInUserService.save(new SignIn(null, findUser.getId(), date, ip));
            return findUser;
        }
        return null;
    }
}
