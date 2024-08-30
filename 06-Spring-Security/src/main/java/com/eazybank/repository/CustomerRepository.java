package com.eazybank.repository;

import com.eazybank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Optional<Customer> findByEmail(String email);
    public Optional<Customer> findByUsername(String username);
    public Optional<List<Customer>> findByEmailOrUsername(String email, String username);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
