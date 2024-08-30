package com.eazybank.repository;

import com.eazybank.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByCustomerId(int customerId);

}
