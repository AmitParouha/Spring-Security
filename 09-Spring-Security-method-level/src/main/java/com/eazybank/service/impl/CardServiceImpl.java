package com.eazybank.service.impl;

import com.eazybank.entity.Card;
import com.eazybank.repository.CardRepository;
import com.eazybank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;

    @Override
    public List<Card> getCardDetails(int customerId) {
        return cardRepository.findByCustomerId(customerId);
    }

}
