package com.revature.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString(exclude = "password")
@NoArgsConstructor
public class UserSignUpRequest {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Size(min = 6, max = 64, message = "Password length should be between 6 and 64 characters")
    @NotBlank(message = "Password cannot be blank")
    private String password;

}
