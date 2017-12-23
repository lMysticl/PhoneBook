package com.mystic.model.dto;

import com.mystic.model.entity.User;
import lombok.*;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */
@Data
@Setter
@Getter
public class ContactDTO {

    private long contactId;

    private long userId;

    private String firstname;

    private String lastname;

    private String middlename;

    private String mobilePhone;

    private String homePhone;

    private String address;

    private String email;

    private User user;
}
