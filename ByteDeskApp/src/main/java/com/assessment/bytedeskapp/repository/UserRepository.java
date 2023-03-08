package com.assessment.bytedeskapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.assessment.bytedeskapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

Optional<User> findByUserName(String userName);

	

	
}
