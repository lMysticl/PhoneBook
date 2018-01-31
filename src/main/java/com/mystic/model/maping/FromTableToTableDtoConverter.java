package com.mystic.model.maping;

import com.mystic.model.dto.ContactDTO;
import com.mystic.model.entity.Contact;
import org.modelmapper.AbstractConverter;

import java.util.Objects;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
public class FromTableToTableDtoConverter extends AbstractConverter<Contact, ContactDTO> {
    @Override
    protected ContactDTO convert(Contact contact) {
        if (Objects.nonNull(contact)) {
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setAddress(contact.getAddress());
            contactDTO.setEmail(contact.getEmail());
            contactDTO.setContactId(contact.getContactId());
            contactDTO.setFirstname(contact.getFirstname());
            contactDTO.setLastname(contact.getLastname());
            contactDTO.setMiddlename(contact.getMiddlename());
            contactDTO.setHomePhone(contact.getMiddlename());
            contactDTO.setUser(contact.getUser());
            contactDTO.setUserId(contact.getUserId());
            contactDTO.setMobilePhone(contact.getMobilePhone());

            return contactDTO;
        }
        return new ContactDTO();
    }


}
