package com.eazybank.service;

import com.eazybank.entity.Card;

import java.util.List;

public interface CardService  {

    public List<Card> getCardDetails(int customerId);

}
