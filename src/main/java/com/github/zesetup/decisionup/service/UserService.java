
package com.github.zesetup.decisionup.service;

import com.github.zesetup.decisionup.domain.User;
import com.github.zesetup.decisionup.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class UserService extends CrudService<User, String> {

    private UserRepository repository;

    public UserService(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

}
