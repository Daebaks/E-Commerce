package com.revature.mapper;


import com.revature.model.dto.UserInfoDto;
import com.revature.model.dto.UserResponse;
import com.revature.model.dto.UserSignUpRequest;
import com.revature.model.entity.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper {

    public User toEntity(UserSignUpRequest userSignUpRequest, String encodedPassword) {
        if (ObjectUtils.isEmpty(userSignUpRequest)) {
            return null;
        }
        User user = new User();
        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setUsername(userSignUpRequest.getUsername());
        return user;
    }

    public UserInfoDto toDto(User user) {
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        return UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }

    public UserResponse toResponse(User user) {
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        String createdAt = mapDateToDto(user.getCreatedAt());
        String updatedAt = mapDateToDto(user.getUpdatedAt());
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
