//package com.mystic.model.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
///**
// * @author Putrenkov Pavlo
// */
//
//@Entity
//@Table(name = "contacts")
//@Getter
//@Setter
//@EqualsAndHashCode
//public class Contact implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "contact_id")
//    private long contactId;
//
//    @Column(name = "user_id")
//    private long userId;
//
//    @Column(name = "firstname")
//    private String firstname;
//
//    @Column(name = "lastname")
//    private String lastname;
//
//    @Column(name = "middlename")
//    private String middlename;
//
//    @Column(name = "mobile_phone")
//    private String mobilePhone;
//
//    @Column(name = "home_phone")
//    private String homePhone;
//
//    @Column(name = "address")
//    private String address;
//
//    @Column(name = "email")
//    private String email;
//
//    @ManyToOne()
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    @JsonIgnore
//    private User user;
//
//    @Override
//    public String toString() {
//        return "Contact{" +
//                "contactId=" + contactId +
//                ", userId=" + userId +
//                ", firstname='" + firstname + '\'' +
//                ", lastname='" + lastname + '\'' +
//                ", middlename='" + middlename + '\'' +
//                ", mobilePhone='" + mobilePhone + '\'' +
//                ", homePhone='" + homePhone + '\'' +
//                ", address='" + address + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
//}
