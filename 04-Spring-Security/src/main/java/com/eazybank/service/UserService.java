package com.eazybank.service;

import com.eazybank.entity.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> getUserByEmail(String email);
    public Optional<User> getUserByUserName(String userName);
    public Optional<User> getUserByEmailOrUserName(String email, String userName);
    public boolean checkUserExistsByUserName(String userName);
    public boolean checkUserExistsByEmail(String email);


}
