package com.github.zesetup.decisionup.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class User {
    @Id
    String id;

    @Size(min = 2, max = 16)
    String name;

    @Size(min = 2, max = 16)
    String surname;

    @Size(min = 8, max = 32)
    String password;

    Role role;

    @ManyToOne
    Company company;

    public User(String id, String name, String surname, String password, Role role) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }
}
