package com.example.assign.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority(@adminUpdate)")
    @PutMapping("/update/role/{id}/{authorize}")
    public ResponseEntity<?> updateRole(@PathVariable("id") UUID uuid,
                                        @PathVariable("authorize") String authorize) {
        userService.addRoleUser(uuid, authorize);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminDelete)")
    @DeleteMapping("/delete/role/{id}/{authorize}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") UUID uuid,
                                        @PathVariable("authorize") String authorize) {
        userService.removeRoleUserByCode(uuid, authorize);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority(@adminRead)")
    @GetMapping("/find-all/{status}")
    public ResponseEntity<List<UserDTO>> getAllUser(@PathVariable("status") Integer status) {
        return new ResponseEntity<>(userService.findUsersByStatus(status), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminDelete)")
    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        userService.deleteUser(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
