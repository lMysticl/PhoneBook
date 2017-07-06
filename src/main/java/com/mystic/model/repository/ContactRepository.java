package com.mystic.model.repository;

import com.mystic.model.entity.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


/**
 * @author Putrenkov Pavlo
 */

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    ArrayList<Contact> findByUserId(Long userId);


    Contact saveAndFlush(Contact contact);

    @Modifying
    @Query(value = "UPDATE Contacts SET firstname = ?2, lastname = ?3, middlename = ?4, mobile_phone = ?5, home_phone = ?6, address = ?7, email = ?8 WHERE contact_id = ?1", nativeQuery = true)
    void updateContact(Long contactId,
                       String firstname,
                       String lastname,
                       String middlename,
                       String mobilePhone,
                       String homePhone,
                       String address,
                       String email);

}
