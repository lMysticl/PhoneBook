package com.mystic.model.service;

import com.mystic.model.entity.Contact;
import com.mystic.model.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author Putrenkov Pavlo
 */

@Component("contactService")
@AllArgsConstructor
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;

    public ArrayList<Contact> getByUserId(Long userId) {
        return contactRepository.findByUserId(userId);

    }

    public Long deleteByUserId(Long userId) {
        return contactRepository.deleteByUserId(userId);
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
