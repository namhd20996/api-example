package com.example.assign.role;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    void addRole(RoleAddRequest request);

    void updateRole(UUID uuid, RoleAddRequest request);

    void deleteRole(String code);

    List<RoleDTO> findRoles();
}
