package com.mystic.model.dto;

import com.mystic.model.entity.Contact;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
@Data
@Getter
@Setter
public class UserDTO {

    private long userId;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String middlename;

    private List<Contact> contacts = new ArrayList<>();

}
