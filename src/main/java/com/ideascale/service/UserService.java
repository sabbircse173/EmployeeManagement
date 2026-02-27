package com.ideascale.service;

import com.ideascale.data.RegisterData;
import com.ideascale.entity.User;

public interface UserService {

    User registerUser(RegisterData registerData);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
