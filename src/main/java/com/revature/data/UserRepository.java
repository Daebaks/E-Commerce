package com.revature.data;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User getByUsername(String username);

	public int insert(User dummyUser);
}
