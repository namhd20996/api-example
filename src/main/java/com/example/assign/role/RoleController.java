package com.example.assign.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return new ResponseEntity<>(roleService.findRoles(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody RoleAddRequest request) {
        roleService.addRole(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<?> updateRole(@PathVariable("uuid") UUID uuid, @RequestBody RoleAddRequest request) {
        roleService.updateRole(uuid, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRole(@RequestParam("code") String code) {
        roleService.deleteRole(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
