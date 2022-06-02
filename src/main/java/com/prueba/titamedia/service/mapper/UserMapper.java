package com.prueba.titamedia.service.mapper;

import com.prueba.titamedia.domain.User;
import com.prueba.titamedia.service.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User> {

    UserDTO toDto(User bank);

    User toEntity(UserDTO userDTO);

    default User fromId(UUID id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
