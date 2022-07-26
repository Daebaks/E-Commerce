package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.revature.model.Product;
import com.revature.model.User;
import com.revature.service.UserService;

@RestController
@RequestMapping("users") // -> localhost:8080/users
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService us;

	// Login
	@PostMapping("login")
	public ResponseEntity<User> login(@RequestBody LoginObj loginObj) {
		User u = us.login(loginObj.username, loginObj.password);
		if (u == null) {
			return new ResponseEntity<User>(u, HttpStatus.NO_CONTENT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("id", String.valueOf(u.getId()));
		return new ResponseEntity<User>(u, headers, HttpStatus.OK);
	}

	// Get by Username
	@GetMapping("{username}") // -> localhost:8080/users/bserfozo -> pull info for user with username bserfozo
	public User getUser(@PathVariable("username") String username) {
		return us.getByUsername(username);
	}

	@PostMapping
	public User createNewUser(@RequestBody User u) {
		return us.add(u);
	}

	@PutMapping
	public User updateUser(@RequestBody User u) {
		return us.update(u);
	}

	@PostMapping("addtocart/{sku}")
	public ResponseEntity<User> addToCart(@PathVariable("sku") Long sku, @RequestHeader("user_id") int id) {
		User u = us.addToCart(id, sku);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@PostMapping("removefromcart/{sku}")
	public ResponseEntity<User> removeFromCart(@PathVariable("sku") Long sku, @RequestHeader("user_id") int id) {
		User u = us.removeFromCart(id, sku);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@PutMapping("clear/{id}")
	public ResponseEntity<User> clearCart(@PathVariable("id") int id) {
		User u = us.clearCart(id);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@GetMapping("cart/{id}")
	public List<Product> getCartList(@PathVariable("id") int id){
		 return us.getCartItems(id);
	}
}
class LoginObj {
	public String username;
	public String password;
}