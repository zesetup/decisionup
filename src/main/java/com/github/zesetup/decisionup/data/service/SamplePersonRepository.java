package com.github.zesetup.decisionup.data.service;

import com.github.zesetup.decisionup.data.entity.SamplePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, Integer> {

}