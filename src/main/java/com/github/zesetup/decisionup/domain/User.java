package com.github.zesetup.decisionup.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class User extends AbstractEntity {
    @Id
    String id;

    @Size(min = 2, max = 16)
    String name;

    @Size(min = 2, max = 16)
    String surname;

    @Email
    String email;

    @Size(min = 8, max = 32)
    String password;

    Role role;

    @ManyToOne
    Company company;

    public User(String id, String name, String surname, String email, String password, Role role) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
