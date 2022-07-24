package com.revature.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewUserResponse {

    private Long id;

    private String username;

    private String email;

    private String createdAt;

    private String updatedAt;


}
