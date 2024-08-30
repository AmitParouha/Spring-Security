package com.eazybank.service.impl;

import com.eazybank.entity.User;
import com.eazybank.repository.UserRepository;
import com.eazybank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class UserServiceImpl implements  UserService {

    private UserRepository userRepository;


    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Optional<User> getUserByEmailOrUserName(String email, String userName) {
        return userRepository.findByEmailOrUserName(email, userName);
    }

    @Override
    public boolean checkUserExistsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean checkUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
