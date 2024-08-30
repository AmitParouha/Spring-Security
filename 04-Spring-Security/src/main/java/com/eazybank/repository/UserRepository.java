package com.eazybank.repository;

import com.eazybank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
    public Optional<User> findByUserName(String userName);
    public Optional<User> findByEmailOrUserName(String email, String userName);
    public boolean existsByUserName(String userName);
    public boolean existsByEmail(String email);

}
