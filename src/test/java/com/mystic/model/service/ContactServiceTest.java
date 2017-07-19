package com.mystic.model.service;

import com.mystic.model.entity.Contact;
import com.mystic.model.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * @author Pavel Putrenkov
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ContactServiceTest {

    private static final Long userId=0L;

    @InjectMocks
    private ContactService contactService;
    @Mock
    private ContactRepository contactRepository;



    private List<Contact> contacts = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
    when(contactRepository.findByUserId(userId)).thenReturn((ArrayList<Contact>) contacts);
    }

    @Test
    public void getByUserId() throws Exception {
        assertEquals(contactService.getByUserId(userId), contacts);
    }


}