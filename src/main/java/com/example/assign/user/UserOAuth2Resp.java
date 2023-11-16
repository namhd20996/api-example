package com.example.assign.user;

import com.example.assign.role.RoleDTO;

import java.util.List;

public record UserOAuth2Resp(
        String name,
        String picture,
        String email,
        List<RoleDTO> roles
) {
}
