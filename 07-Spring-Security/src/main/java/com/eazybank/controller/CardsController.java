package com.eazybank.controller;

import com.eazybank.entity.Card;
import com.eazybank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardsController {
    private CardService cardService;

    @GetMapping("/myCards/{customerId}")
    public ResponseEntity<List<Card>> getCardDetails(@PathVariable int customerId){
        List<Card> cards = cardService.getCardDetails(customerId);
        if (cards != null){
            return ResponseEntity.ok(cards);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


    }
}
