package com.mystic.model.pojos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Putrenkov Pavlo
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserRegistration {

    private String username;
    private String password;
    private String passwordConfirmation;

    public UserRegistration() {
    }

    public UserRegistration(String username, String password, String passwordConfirmation) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

}
