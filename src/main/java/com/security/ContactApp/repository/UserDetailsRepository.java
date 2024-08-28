package com.security.ContactApp.repository;


import com.security.ContactApp.dao.User_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<User_Details,Integer> {

}
