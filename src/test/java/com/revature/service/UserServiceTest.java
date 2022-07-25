package com.revature.service;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.data.UserRepository;
import com.revature.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository uRepo;

	private UserService uService;

	@BeforeEach
	void setUp() {
		uService = new UserService(uRepo);
	}

 
	/* Testing getByUsername() */
	@Test
	public void testgetByUsernameSuccessfully() {
		User test = new User("alialrubaye", "asdasdasd", "ali@hotmail.com");

		when(uRepo.getByUsername("alialrubaye")).thenReturn(test);

		User returnedUser = uService.getByUsername("alialrubaye");

		assertEquals(test, returnedUser);

	}
 

}
