package com.example.assign.user;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    boolean existsUserByUsername(String username);

    void register(UserRegistrationRequest dto);

    UserDTO authenticate(UserLoginRequest dto);

    void authenticate(OAuth2User oAuth2User);

    String confirmToken(String token);

    boolean existsByEmail(String email);

    void updateUser(UserUpdateRequest dto);

    void addRoleUser(UUID uuid, String authorize);

    void findUserByStatusAndEmail(String email);

    void changePassword(String passwordOld, String passwordNew);

    void removeRoleUserByCode(UUID uuid, String authorize);

    List<UserDTO> findUsersByStatus(Integer status);

    void deleteUser(UUID uuid);
}
