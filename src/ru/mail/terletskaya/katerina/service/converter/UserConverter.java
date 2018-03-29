package ru.mail.terletskaya.katerina.service.converter;

import ru.mail.terletskaya.katerina.repository.model.User;
import ru.mail.terletskaya.katerina.service.model.UserDTO;

public class UserConverter {
    public UserDTO converteUserToUserDTO(User user){
        return UserDTO.newBuilder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
