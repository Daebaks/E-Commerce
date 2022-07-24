package com.revature.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User getByUsername(String username);

	void deleteById(Long id);

}
