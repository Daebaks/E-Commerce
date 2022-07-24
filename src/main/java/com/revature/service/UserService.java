package com.revature.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.revature.exception.*;
import com.revature.mapper.UserMapper;
import com.revature.model.dto.UserInfoDto;
import com.revature.model.dto.UserResponse;
import com.revature.model.dto.UserSignUpRequest;
import com.revature.model.type.ErrorResponseStatusType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.repository.ProductRepository;
import com.revature.repository.UserRepository;
import com.revature.model.entity.Product;
import com.revature.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;


	@Transactional
	public UserInfoDto createUser(UserSignUpRequest userSignUpRequest) {
		log.info("[createUser] invoked with userSignUpRequest=[{}]", userSignUpRequest);
		User user = userRepository.getByUsername(userSignUpRequest.getUsername());
		if (!Objects.isNull(user)) {
			log.error("[createUser] User [{}] already exists", userSignUpRequest.getUsername());
			throw new ApplicationAuthenticationException(ErrorResponseStatusType.USER_ALREADY_EXISTS_EXCEPTION);
		}
		String encodedPassword = passwordEncoder.encode(userSignUpRequest.getPassword());
		user = userMapper.toEntity(userSignUpRequest, encodedPassword);
		user = userRepository.save(user);
		return userMapper.toDto(user);
	}

	public UserInfoDto getUserByUsername(String username) {
		User user = userRepository.getByUsername(username);
		if (Objects.isNull(user)) {
			log.error("[createUser] User [{}] not found exists", username);
			throw new NotFoundException(ErrorResponseStatusType.USER_NOT_FOUND_EXCEPTION, username);
		}
		return userMapper.toDto(user);
	}

	public UserResponse getUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException(ErrorResponseStatusType.USER_NOT_FOUND_EXCEPTION, userId));
		return userMapper.toResponse(user);
	}

	@Transactional
	public void deleteById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException(ErrorResponseStatusType.USER_NOT_FOUND_EXCEPTION, userId));
		userRepository.delete(user);
	}

	public Set<User> findAll(){
		// return from the user repository the findall method but stream it to a set
		return userRepository.findAll().stream().collect(Collectors.toSet());
	}
	// Find by username

	public User getByUsername(String username) {
		User u = userRepository.getByUsername(username);
		if(u==null) {
			throw new UserNotFoundException("No User found with username: " + username);
		}
		return u;
	}
//	public User getById(Long id) {
//		if( id<=0) {
//			log.warn("Id cannot be Zero: {}", id);
//			return null;
//		}
//		return userRepository.getById(id);

//	}

	public User add(User u) {
		Set<User> userz = findAll();
		for(User usr: userz) {
			if(usr.getUsername().equalsIgnoreCase(u.getUsername())) {
				throw new ApplicationAuthenticationException(ErrorResponseStatusType.USER_ALREADY_EXISTS_EXCEPTION);
			}
		}
		User returnedUser = userRepository.save(u);
		if (returnedUser.getId() >0) {
			log.info("Successfully returned user with id {}", returnedUser.getId());
		} else {
			log.warn("Could not add user");
		}
		return returnedUser;
	}



	public User update(User u) {
		User userToUpdate = userRepository.findById(u.getId()).get();
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
					throw new ApplicationAuthenticationException(ErrorResponseStatusType.USER_ALREADY_EXISTS_EXCEPTION);
				}
			}
			userToUpdate.setUsername(u.getUsername());
		}
		if(u.getPassword()!=null) {
			userToUpdate.setPassword(u.getPassword());
		}
		return userRepository.save(userToUpdate);
	}

	public User login(String username, String password) {
		User u = userRepository.getByUsername(username);
		log.info(u.toString());
		if (u==null) {
			throw new UserNotFoundException("Wrong username or user doesn't exist");
		}
		if(!u.getPassword().equals(password)){
			throw new WrongPasswordException("The password did not match with our records");
		}
		return u;
	}
	
	public User addToCart(Long id, Long sku) {
		User u = userRepository.findById(id).get();
		Product p = productRepository.getReferenceBySku(sku);
		for(Product pr: u.getCart()) {
			if(pr.equals(p)) {
				throw new ProductAlreadyInCartException("The product is already in your cart");
			}
		}
		if(p.getQuantity()==0) {
			throw new ProductOutOfStockException("Product is out of stock");
		}
		List<Product> cart = u.getCart();
		cart.add(p);
		u.setCart(cart);
		p.setQuantity(p.getQuantity()-1);
		productRepository.save(p);
		userRepository.save(u);
		return u;
	}
	
	public User removeFromCart(Long id, Long sku) {
		User u = userRepository.findById(id).get();
		Product p = productRepository.getReferenceBySku(sku);
		List<Product> cart = u.getCart();
		cart.remove(p);
		u.setCart(cart);
		p.setQuantity(p.getQuantity()+1);
		productRepository.save(p);
		userRepository.save(u);
		return u;
	}
	
	
}
