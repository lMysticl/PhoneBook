package com.forestyura.controller;

import com.forestyura.model.entity.Contact;
import com.forestyura.model.entity.User;
import com.forestyura.model.service.ContactService;
import com.forestyura.model.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  @author Putrenkov Pavlo
 */

@RestController
@AllArgsConstructor
public class ContactController {

    private final UserService userService;

    private final ContactService contactService;

    @RequestMapping(value = "/contacts/get-all", method = RequestMethod.GET)
    public ResponseEntity returnAllContact() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());

        List<Contact> contacts = contactService.getByUserId(user.getUserId());
        //System.out.println(contacts);
        if (contacts.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("User does not have contacts");
        } else {
            return ResponseEntity
                    .ok(contacts);
        }
    }

    @RequestMapping(value = "contacts/add", method = RequestMethod.POST)
    public Contact addContact(Contact contact) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        contact.setUserId(user.getUserId());

        return contactService.saveContact(contact);
    }

    @RequestMapping(value = "contacts/delete", method = RequestMethod.POST)
    public void deleteContact(Contact contact) {
        contactService.deleteByContactId(contact.getContactId());
    }

    @RequestMapping(value = "contacts/update", method = RequestMethod.POST)
    public void updateContact(Contact contact) {
        contactService.updateContact(contact);
    }
}
