package com.security.ContactApp.repository;

import com.security.ContactApp.dao.User_Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<User_Registration,Integer> {

    User_Registration findByuserName(String userName);
}
