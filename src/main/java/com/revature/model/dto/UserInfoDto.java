package com.revature.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class UserInfoDto {

    private Long id;

    private String email;

    private String password;

    private String username;

}
