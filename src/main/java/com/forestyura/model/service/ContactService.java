package com.forestyura.model.service;

import com.forestyura.model.entity.Contact;
import com.forestyura.model.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by Yuriy Vlasiuk on 07.03.2017.
 */

@Component("contactService")
@Transactional
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public ArrayList<Contact> getByUserId (long userId) {
        return contactRepository.findByUserId(userId);
    }

    public Long deleteByContactId (long userId) {
        return contactRepository.deleteByContactId(userId);
    }

    public Contact saveContact(Contact contact){
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
