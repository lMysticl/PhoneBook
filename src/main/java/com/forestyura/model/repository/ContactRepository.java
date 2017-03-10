package com.forestyura.model.repository;

import com.forestyura.model.entity.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


/**
 * Created by Yuriy Vlasiuk on 07.03.2017.
 */

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    ArrayList<Contact> findByUserId(long userId);
    Long deleteByContactId(long userId);
    Contact saveAndFlush(Contact contact);

    @Modifying
    @Query(value = "update Contacts set firstname = ?2, lastname = ?3, middlename = ?4, mobile_phone = ?5, home_phone = ?6, address = ?7, email = ?8 where contact_id = ?1", nativeQuery = true)
    void updateContact(Long contactId,
                       String firstname,
                      String lastname,
                       String middlename,
                       String mobilePhone,
                       String homePhone,
                       String address,
                       String email);

}
