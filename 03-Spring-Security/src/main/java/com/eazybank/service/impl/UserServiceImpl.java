package com.eazybank.service.impl;

import com.eazybank.entity.User;
import com.eazybank.payload.RegisterDto;
import com.eazybank.repository.UserRepository;
import com.eazybank.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;
    private PasswordEncoder passwordEncoder;

    // Register new User
    @Override
    public RegisterDto registerUser(RegisterDto registerDto) {

        if(userRepository.existsByEmail(registerDto.getEmail())
                || userRepository.existsByUserName(registerDto.getUserName())){

            return null;
        }

        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodedPassword);
        registerDto.setUserRole("USER");

        User user = userRepository.save(mapToUser(registerDto));
        return mapToRegisterDto(user);
    }

    // get user by email
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // get user by username
    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    // get user by email or username
    @Override
    public Optional<User> getUserByEmailOrUserName(String email, String userName) {
        return userRepository.findByEmailOrUserName(email, userName);
    }

    // check user is existed or not using username
    @Override
    public boolean checkUserExistsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean checkUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }




    private User mapToUser(RegisterDto registerDto){
        return mapper.map(registerDto, User.class);
    }

    private RegisterDto mapToRegisterDto(User user){
        return mapper.map(user, RegisterDto.class);
    }
}
