package com.example.assign.role;

import com.example.assign.exception.ResourceDuplicateException;
import com.example.assign.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    private final RoleDTOMapper roleDTOMapper;

    @Override
    public void addRole(RoleAddRequest request) {
        boolean isValidRole = roleRepo.existsByNameAndCode(request.getName(), request.getCode());
        if (isValidRole) {
            throw new ResourceDuplicateException("role name: " + request.getName() +
                    " role code: " + request.getCode() + " is already!.");
        }

        roleRepo.save(new Role(request.getName(), request.getCode()));
    }

    @Override
    public void updateRole(UUID uuid, RoleAddRequest request) {
        boolean isValidRole = roleRepo.existsByNameAndCode(request.getName(), request.getCode());
        if (isValidRole) {
            throw new ResourceDuplicateException("role name: " + request.getName() +
                    " role code: " + request.getCode() + " is already!.");
        }

        Role role = roleRepo.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("find role by id: " + uuid + " not found!."));

        if (!role.getName().equals(request.getName())) {
            role.setName(request.getName());
        }

        if (!role.getCode().equals(request.getCode())) {
            role.setCode(request.getCode());
        }

        roleRepo.save(role);
    }

    @Override
    public void deleteRole(String code) {
        Role role = roleRepo.findRoleByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("role find by code: " + code + " not found!."));
        roleRepo.delete(role);
    }

    @Override
    public List<RoleDTO> findRoles() {
        return roleRepo.findAll()
                .stream()
                .map(roleDTOMapper::toDTO)
                .toList();
    }
}
