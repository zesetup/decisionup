package com.github.zesetup.decisionup.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Company {
    @Id
    String id;

    @Size(min = 2, max = 16)
    String name;

    public Company(String id, String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
