package com.eazybank.controller;

import com.eazybank.entity.Contact;
import com.eazybank.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Random;

@AllArgsConstructor
@RestController
@RequestMapping("/contact")
public class ContactController {
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> saveContactInquiryDetails(@RequestBody Contact contact){
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        Contact createdContact = contactService.saveContactInquiryDetails(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    public String getServiceReqNumber(){
        Random random = new Random();
        int ranNum = random.nextInt(999999999-9999)+9999;
        return "SR"+ranNum;
    }
}
