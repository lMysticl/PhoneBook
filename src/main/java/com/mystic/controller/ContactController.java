package com.mystic.controller;

import com.mystic.model.dto.UserDTO;
import com.mystic.model.entity.Contact;
import com.mystic.model.entity.User;
import com.mystic.service.impl.ContactServiceImpl;
import com.mystic.service.impl.UserServiceImpl;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Putrenkov Pavlo
 */
@RestController
@AllArgsConstructor
public class ContactController {

    private final UserServiceImpl userServiceImpl;
    private final ContactServiceImpl contactServiceImpl;


    @GetMapping(value = "/contacts/get-all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Contact> returnAllContact() {

        User user = userServiceImpl.getUser((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        ModelMapper modelMapper = new ModelMapper();

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return contactServiceImpl.getByUserId(userDTO.getUserId());


    }

    @PostMapping(value = "contacts/add")
    public Contact addContact(Contact contact) {
        System.out.println("!!!!" + contact);
        User user = userServiceImpl.getUser((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        contact.setUserId(user.getUserId());

        return contactServiceImpl.saveContact(contact);
    }

    @PostMapping(value = "contacts/delete")
    public void deleteContact(Contact contact) {
        contactServiceImpl.deleteByUserId(contact.getContactId());

    }


    @PostMapping(value = "contacts/deleteList")
    public void deleteContactList( String contactId) {
        System.out.println("contactId!!!!!!! "+contactId);
        List<String> items = Arrays.asList(contactId.split(","));

        for (String item : items) {
            contactServiceImpl.deleteByUserId(Long.valueOf(item));
        }

    }


    @PostMapping(value = "contacts/update")
    public void updateContact(Contact contact) {
        System.out.println("!!!!" + contact);
        User user = userServiceImpl.getUser((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        contact.setUserId(user.getUserId());
        contactServiceImpl.updateContact(contact);
    }
}
