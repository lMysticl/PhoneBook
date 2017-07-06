package com.mystic.model.service;

import com.mystic.model.entity.Contact;
import com.mystic.model.entity.User;
import com.mystic.model.repository.ContactRepository;
import com.mystic.model.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

/**
 * @author Pavel Putrenkov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTest {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;


    private Long userId;
    private User user;
    private Contact contact;
    private Contact contactLoc;

    @Before
    public void setUp() throws Exception {

        user = new User();

        User savedUser = userRepository.saveAndFlush(user);
        AtomicReference<List<Contact>> contactList = new AtomicReference<>(new ArrayList<>());
        contactLoc = new Contact();
        contactList.get().add(contactLoc);
        contactLoc.setUserId(user.getUserId());

        contactLoc.setUser(savedUser);
        this.contact = contactRepository.saveAndFlush(contactLoc);
    }

    @After
    public void tearDown() throws Exception {
       contactRepository.delete(contact.getContactId());
        userRepository.delete(user.getUserId());
    }

    @Test
    public void getByUserId() throws Exception {
        ArrayList<Contact> actual = contactRepository.findByUserId(user.getUserId());
        String expect = "[Contact{contactId=" + contact.getContactId() + ", userId=" + user.getUserId() + ", firstname='null', lastname='null', middlename='null', mobilePhone='null', homePhone='null', address='null', email='null'}]";

        assertEquals(actual.toString(), expect);
    }
//TODO Complete Test's
//    @Test
//    public void deleteByContactId() throws Exception {
//        contactRepository.delete(contact.getContactId());
//        assertNull(contactRepository.findOne(contact.getContactId()));
//    }

    @Test
    public void saveContact() throws Exception {
        contactLoc.setEmail("email");
        contactLoc.setAddress("address");
        contactLoc.setFirstname("firstName");
        contactLoc.setLastname("lastName");
        contactLoc.setMiddlename("address");
        contactLoc.setMobilePhone("+111111111111");
        contactLoc.setEmail("mail@mail.com");
        contactLoc.setContactId(contact.getContactId());
        String expect = "Contact{contactId=" + contact.getContactId() + ", userId=" + user.getUserId() + ", firstname='firstName', lastname='lastName', middlename='address', mobilePhone='+111111111111', homePhone='null', address='address', email='mail@mail.com'}";
        String actual = contact.toString();
        assertEquals(expect, actual);
    }

    @Test
    public void updateContact() throws Exception {
        contactLoc.setEmail("email2");
        contactLoc.setAddress("address2");
        contactLoc.setFirstname("firstName2");
        contactLoc.setLastname("lastName2");
        contactLoc.setMiddlename("address2");
        contactLoc.setMobilePhone("+222222222");
        contactLoc.setEmail("mail2@mail.com");
        contactLoc.setContactId(contact.getContactId());
        String expect = "Contact{contactId=" + contact.getContactId() + ", userId=" + user.getUserId() + ", firstname='firstName2', lastname='lastName2', middlename='address2', mobilePhone='+222222222', homePhone='null', address='address2', email='mail2@mail.com'}";
        String actual = contact.toString();
        assertEquals(expect, actual);
    }

}