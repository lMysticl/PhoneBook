package com.mystic.service;

import com.mystic.model.entity.Contact;
import com.mystic.model.repository.ContactRepository;
import com.mystic.service.impl.ContactServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * This test only demo
 *
 * @author Pavel Putrenkov
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ContactServiceImplTest {

    private static final Long USER_ID = 0L;

    @InjectMocks
    private ContactServiceImpl contactServiceImpl;
    @Mock
    private ContactRepository contactRepository;


    private Contact result;
    private Contact actual;

    private List<Contact> contacts = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        result = mock(Contact.class);
        actual = mock(Contact.class);
        when(contactRepository.findByUserId(USER_ID)).thenReturn((ArrayList<Contact>) contacts);
    }

    @Test
    public void getByUserId() {
        assertEquals(contactServiceImpl.getByUserId(USER_ID), contacts);
    }

    @Test
    public void saveContact() {

        actual.setUserId(USER_ID);
        result.setUserId(USER_ID);

        when(contactServiceImpl.saveContact(actual)).thenReturn(result);
    }

    @Test
    public void deleteByUserId() {
        doThrow(new RuntimeException()).when(contactRepository).delete(contactRepository.getOne(USER_ID));
    }

}