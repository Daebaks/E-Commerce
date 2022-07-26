package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.data.ProductRepository;
import com.revature.data.UserRepository;
import com.revature.model.Product;
import com.revature.model.User;

 
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository uRepo;

	@Mock
	private ProductRepository pRepo;

	private UserService uService;
	private ProductService pService;

	private User user1;
	private User user2;
	private User user3;
	private Product pro1;
	private Product pro2;

	@BeforeEach
	public void setUp() {
		uService = new UserService(uRepo);
		pService = new ProductService(pRepo);
		 
	}

	@AfterEach
	public void tearDown() {
		user1 = null;
		user2 = null;
		user3 = null;
		pro1 = null;
		pro2 = null;
		uService = null;
		pService = null;
	}

	/* Testing getByUsername() */
	@Test
	public void testgetByUsernameSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		when(uRepo.getByUsername("johnsmith")).thenReturn(user1);
		user2 = uService.getByUsername("johnsmith");
		assertEquals(user1, user2);
	}

	/* Testing getById() */
	@Test
	public void testgetByIdSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(2);
		when(uRepo.getReferenceById(2)).thenReturn(user1);
		user2 = uService.getById(2);
		assertEquals(user1, user2);
	}

	/* Testing login() */
	@Test
	public void testLoginSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(2);
		when(uRepo.getByUsername("johnsmith")).thenReturn(user1);
		user2 = uService.login("johnsmith", "asdasdasd");
		assertEquals(user1, user2);
	}

	/* Testing add() */
	@Test
	public void testAddSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(2);
		when(uRepo.save(user1)).thenReturn(user1);
		user2 = uService.add(user1);
		assertEquals(user1, user2);
	}

	/* Testing update() */
	@Test
	public void testUpdateSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(2);
		when(uRepo.getReferenceById(user1.getId())).thenReturn(user1);

		// update object
		user2 = new User("jackloyed", "123456", "jack@hotmail.com");
		user2.setId(user1.getId());

		when(uRepo.save(user2)).thenReturn(user2);

		user3 = uService.update(user2);

		assertEquals(user2, user3);
		assertEquals(user3.getId(), user1.getId());

	}

	/* Testing findAll() */
	@Test
	public void testFindAllSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(2);
		user2 = new User("jackloyed", "123456", "jack@hotmail.com");
		user2.setId(3);

		List<User> userList =new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		 
		when(uRepo.findAll()).thenReturn(userList);
		Set<User> uSet = new HashSet<>();
		for(User u: userList) {
			uSet.add(u);
		}
		Set<User> returned = uService.findAll();
		assert uSet.equals(returned);
	}

	/* Testing delete() */
	@Test
	public void testDeleteSuccessfully() {
//		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
//		user1.setId(978);
//		when(uRepo.getReferenceById(978)).thenReturn(user1);
//		//when(uRepo.deleteById(978)).thenReturn(true);
//		when(uRepo.existsById(978)).thenReturn(true);
//		
//		assertEquals(true, uService.delete(978));
	}
	
	
	
}
