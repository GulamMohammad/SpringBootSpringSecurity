package com.becoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.becoder.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Users findByUsername(String username);

}
