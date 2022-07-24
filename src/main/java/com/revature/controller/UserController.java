package com.revature.controller;

import com.revature.model.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.entity.User;
import com.revature.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("users") // -> localhost:8080/users
@CrossOrigin("*")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Operation(summary = "Retrieve currently authenticated user", security = { @SecurityRequirement(name = "bearer-token") })
	@GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse getMe(HttpServletRequest httpServletRequest) {
		Long userId = Long.valueOf(httpServletRequest.getUserPrincipal().getName());
		return userService.getUserById(userId);
	}

	@Operation(summary = "Delete currently authenticated user", security = { @SecurityRequirement(name = "bearer-token") })
	@DeleteMapping
	public void deleteUser(HttpServletRequest httpServletRequest) {
		Long userId = Long.valueOf(httpServletRequest.getUserPrincipal().getName());
		userService.deleteById(userId);
	}

//	@PutMapping
	public User updateUser(@RequestBody User u) {
		return userService.update(u);
	}

//	@PostMapping("addtocart/{sku}")
	public User addToCart(@PathVariable("sku") Long sku, @RequestHeader("user_id") Long id) {
		User u = userService.addToCart(id, sku);
		return u;
	}
	
//	@PostMapping("removefromcart/{sku}")
	public User removeFromCart(@PathVariable("sku") Long sku, @RequestHeader("user_id") Long id) {
		User u = userService.removeFromCart(id, sku);
		return u;
	}
	
}
