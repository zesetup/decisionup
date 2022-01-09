package com.github.zesetup.decisionup.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity
public class Company extends  AbstractEntity {
    @Id
    String id;

    @Size(min = 2, max = 16)
    String name;

    @OneToMany
    Set<User> users;

    public Company(String id, String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Company() {
    }
}
