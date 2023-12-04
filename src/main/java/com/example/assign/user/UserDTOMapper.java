package com.example.assign.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDTOMapper {

    private final ModelMapper mapper;

    public User toEntity(UserDTO dto) {
        return Optional.ofNullable(dto)
                .map(user -> mapper.map(user, User.class))
                .orElse(null);
    }

    public UserDTO toDTO(User entity) {
        return Optional.ofNullable(entity)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElse(null);
    }

}
