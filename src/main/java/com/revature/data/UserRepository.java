package com.revature.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUsername(String username);
	
	public List<User> findByOrderByUsername();
	
	@Query("FROM User WHERE email LIKE %:pattern%")
	public List<User> findByEmailContains(String pattern);

}
