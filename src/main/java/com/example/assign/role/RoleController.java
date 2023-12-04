package com.example.assign.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize("hasAnyAuthority(@adminRead)")
    @GetMapping("/all")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return new ResponseEntity<>(roleService.findRoles(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminCreate)")
    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody RoleAddRequest request) {
        roleService.addRole(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority(@adminUpdate)")
    @PutMapping("/update/{uuid}")
    public ResponseEntity<?> updateRole(@PathVariable("uuid") UUID uuid, @RequestBody RoleAddRequest request) {
        roleService.updateRole(uuid, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminDelete)")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRole(@RequestParam("code") String code) {
        roleService.deleteRole(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
