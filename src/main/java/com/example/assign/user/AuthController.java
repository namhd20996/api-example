package com.example.assign.user;

import com.example.assign.validation.ValidationHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    private final ValidationHandle validationHandle;

    @GetMapping("/get")
    public String get() {
        return "GET:: auth get";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Validated @RequestBody AuthenticationRequest request, Errors errors) {
        validationHandle.handleValidate(errors);
        return new ResponseEntity<>(userService.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserRegistrationRequest request, Errors errors) {
        validationHandle.handleValidate(errors);
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody UserUpdateRequest request, Errors errors) {
        validationHandle.handleValidate(errors);
        userService.updateUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> enabledAuth(@RequestParam("token") String token) {
        return new ResponseEntity<>(userService.confirmToken(token), HttpStatus.OK);
    }

    @PutMapping("/update/role/{id}/{authorize}")
    public ResponseEntity<?> updateRole(@PathVariable("id") UUID uuid,
                                        @PathVariable("authorize") String authorize) {
        userService.addRoleUser(uuid, authorize);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/role/{id}/{authorize}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") UUID uuid,
                                        @PathVariable("authorize") String authorize) {
        userService.removeRoleUserByCode(uuid, authorize);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable("email") String email) {
        userService.findUserByStatusAndEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@Validated @RequestBody UserChangePasswordRequest request,
                                            Errors errors) {
        validationHandle.handleValidate(errors);
        userService.changePassword(request.getPasswordOld(), request.getPasswordNew());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-all/{status}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UserDTO>> getAllUser(@PathVariable("status") Integer status) {
        return new ResponseEntity<>(userService.findUsersByStatus(status), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        userService.deleteUser(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public String logout() {
        return "POST:: auth logout";
    }
}
