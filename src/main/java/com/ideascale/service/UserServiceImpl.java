package com.ideascale.service;

import com.ideascale.data.RegisterData;
import com.ideascale.data.Role;
import com.ideascale.entity.User;
import com.ideascale.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User registerUser(RegisterData registerData) {
        User user = new User();
        user.setUsername(registerData.getUsername());
        user.setEmail(registerData.getEmail());
        user.setPassword(passwordEncoder.encode(registerData.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(true);

        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
