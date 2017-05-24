package com.mystic.model.service;

import com.mystic.model.entity.Contact;
import com.mystic.model.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author Putrenkov Pavlo
 */

@Component("contactService")
@Transactional
@AllArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;


    public ArrayList<Contact> getByUserId(long userId) {
        return contactRepository.findByUserId(userId);

    }

    public void deleteByContactId(long userId) {
        contactRepository.deleteByContactId(userId);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    public void updateContact(Contact contact) {
        contactRepository.updateContact(
                contact.getContactId(),
                contact.getFirstname(),
                contact.getLastname(),
                contact.getMiddlename(),
                contact.getMobilePhone(),
                contact.getHomePhone(),
                contact.getAddress(),
                contact.getEmail());
    }


}
