package com.revature.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.data.UserRepository;
import com.revature.exception.SameEmailExistsException;
import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.exception.UserNotFoundException;
import com.revature.exception.WrongPasswordException;
import com.revature.model.User;

@Service
public class UserService {
	
private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository userRepo;
	
	// Let's add in a couple methods to call upon the Repo
	
	public Set<User> findAll(){
		// return from the user repository the findall method but stream it to a set
		return userRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	// Find by username
	public User getByUsername(String username) {
		User u = userRepo.getByUsername(username);
		if(u==null) {
			throw new UserNotFoundException("No User found with username: " + username);
		}
		return u;
	}
	
	public User getById(int id) {
		if( id<=0) {
			log.warn("Id cannot be Zero: {}", id);
			return null;
		}
		return userRepo.getById(id);
	}
	
	public User add(User u) {
		Set<User> userz = findAll();
		for(User usr: userz) {
			if(usr.getUsername().equalsIgnoreCase(u.getUsername())) {
				throw new UserNameAlreadyTakenException("Sorry, the user name is already taken");
			}
		}
		User returnedUser = userRepo.save(u);
		if (returnedUser.getId() >0) {
			log.info("Successfully returned user with id {}", returnedUser.getId());
		} else {
			log.warn("Could not add user");
		}
		return returnedUser;
	}
	
	public User update(User u) {
		User userToUpdate = userRepo.getById(u.getId());
		if(u.getEmail()!=null) {
			for(User z: findAll()) {
				if(u.getEmail().equalsIgnoreCase(z.getEmail())) {
					throw new SameEmailExistsException("Sorry, this email already exists or you didn't change it");
				}
			}
			userToUpdate.setEmail(u.getEmail());
		}
		if(u.getUsername()!=null) {
			for(User z: findAll()) {
				if(u.getUsername().equalsIgnoreCase(z.getUsername())) {
					throw new UserNameAlreadyTakenException("Sorry, this username already exists or you didn't change it");
				}
			}
			userToUpdate.setUsername(u.getUsername());
		}
		if(u.getPassword()!=null) {
			userToUpdate.setPassword(u.getPassword());
		}
		return userRepo.save(userToUpdate);
	}
	
	public boolean delete(int id) {
		if(userRepo.getById(id)==null) {
			throw new UserNotFoundException("Sorry, the user doesn't exist");
		}else {
		userRepo.deleteById(id);
		return !(userRepo.existsById(id));}
	}

	public User login(String username, String password) {
		User u = userRepo.getByUsername(username);
		if (u==null) {
			throw new UserNotFoundException("Wrong username or user doesn't exist");
		}
		else if(!u.getPassword().equals(password)){
			throw new WrongPasswordException("The password did not match with our records");
		}
		
		return u;
	}
	
	
	
}
