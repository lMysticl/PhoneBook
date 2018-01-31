package com.mystic.service;

import com.mystic.model.entity.Contact;
import com.mystic.model.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Putrenkov Pavlo
 */
@Service("contactService")
@AllArgsConstructor
public class ContactService{

    private  ContactRepository contactRepository;

    public ArrayList<Contact> getByUserId(Long userId) {
        return contactRepository.findByUserId(userId);
    }

    public void deleteByUserId(Long userId) {
        contactRepository.delete(userId);
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
