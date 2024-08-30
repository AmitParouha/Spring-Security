package com.eazybank.service.impl;

import com.eazybank.entity.Contact;
import com.eazybank.repository.ContactRepository;
import com.eazybank.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;

    @Override
    public Contact saveContactInquiryDetails(Contact contact) {
        return contactRepository.save(contact);
    }
}
