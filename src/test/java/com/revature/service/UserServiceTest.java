package com.revature.service;


import com.revature.exception.NotFoundException;
import com.revature.mapper.UserMapper;
import com.revature.model.dto.UserInfoDto;
import com.revature.model.entity.User;
import com.revature.model.type.ErrorResponseStatusType;
import com.revature.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void getUserByUsername_shouldThrowUserNotFoundException() {
        //given
        String username = "test";

        //when
       when(userRepository.getByUsername(anyString())).thenReturn(null);

        //then
        NotFoundException e = assertThrows(NotFoundException.class, () -> userService.getUserByUsername(username));

        assertEquals("User [test] not found", e.getMessage());
        assertEquals(ErrorResponseStatusType.USER_NOT_FOUND_EXCEPTION, e.getResponseStatus());
    }

    @Test
    public void getUserByUsername_shouldReturnUser() {
        //given
        String username = "test";

        //when
        User user = new User();
        user.setUsername("test");
        when(userRepository.getByUsername(anyString())).thenReturn(user);

        UserInfoDto userInfoDto = UserInfoDto.builder().username("test").build();
        when(userMapper.toDto(any())).thenReturn(userInfoDto);

        //then
        UserInfoDto userDto = userService.getUserByUsername(username);

        assertEquals("test", userDto.getUsername());
    }

}
