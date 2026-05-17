package com.emcaras.portfolio.dto;

import com.emcaras.portfolio.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto data){
        User user = new User();
        user.setId(data.getId());
        user.setUsername(data.getUsername());
        user.setEnabled(data.isEnabled());
        return user;
    }

    public UserDto toDto(User data){
        UserDto user = new UserDto();
        user.setId(data.getId());
        user.setUsername(data.getUsername());
//        user.setPassword(data.getPassword());
        user.setEnabled(data.isEnabled());
        return user;
    }
}
