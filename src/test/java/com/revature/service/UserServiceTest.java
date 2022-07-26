package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.revature.exception.UserNameAlreadyTakenException;
import com.revature.exception.UserNotFoundException;
import com.revature.exception.WrongPasswordException;
import com.revature.model.Product;
import com.revature.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository uRepo;

	@Mock
	private ProductRepository pRepo;

	private UserService uService;

	private User user1;
	private User user2;
	private User user3;
	private Product pro1;
	private Product pro2;

	@BeforeEach
	public void setUp() {
		uService = new UserService(uRepo, pRepo);
	}

	@AfterEach
	public void tearDown() {
		user1 = null;
		user2 = null;
		user3 = null;
		pro1 = null;
		pro2 = null;
		uService = null;
	}

	/* Testing getByUsername() */
	@Test
	public void testgetByUsernameSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		when(uRepo.getByUsername("johnsmith")).thenReturn(user1);
		user2 = uService.getByUsername("johnsmith");
		assertEquals(user1, user2);
	}
	@Test
	public void testgetByUsernameNotExistException() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		when(uRepo.getByUsername("johnsddmith")).thenReturn(null);
		assertThrows(UserNotFoundException.class, () -> {
			uService.getByUsername("johnsddmith");
		});
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
	@Test
	public void testLoginUserNotFoundException() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(2);
		when(uRepo.getByUsername("johnsmasdasdith")).thenReturn(null);
		assertThrows(UserNotFoundException.class, () -> {
			uService.login("johnsmasdasdith", "123123123");
		});
		
	}
	@Test
	public void testLoginWrongPasswordException() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(2);
		when(uRepo.getByUsername("johnsmith")).thenReturn(user1);
		assertThrows(WrongPasswordException.class, () -> {
			uService.login("johnsmith", "123123123");
		});
		
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
	@Test
	public void testAddUsernameTakenException() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(2);
		user2 = new User("jackloyed", "123456", "jack@hotmail.com");
		user2.setId(3);

		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);

		when(uRepo.findAll()).thenReturn(userList);
		Set<User> uSet = new HashSet<>();
		for (User u : userList) {
			uSet.add(u);
		}
		user3 = new User("jackloyed", "12asdasd3456", "jackddd@hotmail.com");
		user3.setId(4);
		
		assertThrows(UserNameAlreadyTakenException.class, () -> {
			uService.add(user3);
		});
 		
		
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

		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);

		when(uRepo.findAll()).thenReturn(userList);
		Set<User> uSet = new HashSet<>();
		for (User u : userList) {
			uSet.add(u);
		}
		Set<User> returned = uService.findAll();
		assert uSet.equals(returned);
	}

	
	/* Testing addToCart() */
	@Test
	public void testAddToCartSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(999);

		pro1 = new Product("Bike", 20.99, "Sport", 100, "path.jpg");
		pro1.setSku(7890L);
		when(uRepo.getReferenceById(999)).thenReturn(user1);
		when(pRepo.getReferenceById(7890L)).thenReturn(pro1);

		List<Product> cart = new ArrayList<>();

		user1.setCart(cart);

		when(uRepo.save(user1)).thenReturn(user1);
		user2 = uService.addToCart(999, 7890L);
		cart.add(pro1);

		assertEquals(user1, user2);

	}

	/* Testing getCartItems() */
	@Test
	public void testGetCartItemsSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(999);
		pro1 = new Product("Bike", 20.99, "Sport", 100, "path.jpg");
		pro1.setSku(7890L);

		List<Product> cart = new ArrayList<>();
		cart.add(pro1);
		user1.setCart(cart);

		when(uRepo.getReferenceById(999)).thenReturn(user1);

		List<Product> cartReturned = uService.getCartItems(999);

		assert cartReturned.equals(user1.getCart());

	}

	/* Testing clearCart() */
	@Test
	public void testClearCartSuccessfully() {
		user1 = new User("johnsmith", "asdasdasd", "john@hotmail.com");
		user1.setId(999);
		pro1 = new Product("Bike", 20.99, "Sport", 100, "path.jpg");
		pro1.setSku(7890L);
		List<Product> cart = new ArrayList<>();
		cart.add(pro1);
		user1.setCart(cart);

		when(uRepo.getReferenceById(999)).thenReturn(user1);

		user1.setCart(new ArrayList<>());

		when(uRepo.save(user1)).thenReturn(user1);

		user2 = uService.clearCart(999);

		assertEquals(user1, user2);

	}

}
