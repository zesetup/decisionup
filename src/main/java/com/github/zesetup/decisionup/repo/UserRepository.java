
package com.github.zesetup.decisionup.repo;

import com.github.zesetup.decisionup.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface UserRepository extends JpaRepository<User, String> {

}