package com.mystic.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="app_role")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String name;

    @Column(name="description")
    private String description;

    Role() {}

    public Role(String name) {
        this.name = name;
    }
}
