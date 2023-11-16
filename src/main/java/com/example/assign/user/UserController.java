package com.example.assign.user;

import com.example.assign.validation.ValidationHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;

    private final ValidationHandle validationHandle;


    @GetMapping("/oauth2-success")
    public ResponseEntity<?> authenticate(
            @AuthenticationPrincipal OAuth2User oauth2User
    ) {
        userService.authenticate(oauth2User);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/oauth2-fail")
    public ResponseEntity<?> fail() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @Validated @RequestBody UserLoginRequest request,
            Errors errors
    ) {
        validationHandle.handleValidate(errors);
        return new ResponseEntity<>(userService.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Validated @RequestBody UserRegistrationRequest request,
            Errors errors
    ) {
        validationHandle.handleValidate(errors);
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(
            @Validated @RequestBody UserUpdateRequest request,
            Errors errors
    ) {
        validationHandle.handleValidate(errors);
        userService.updateUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> enabledAuth(@RequestParam("token") String token) {
        return new ResponseEntity<>(userService.confirmToken(token), HttpStatus.OK);
    }

    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable("email") String email) {
        userService.findUserByStatusAndEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @Validated @RequestBody UserChangePwRequest request,
            Errors errors
    ) {
        validationHandle.handleValidate(errors);
        userService.changePassword(request.getPasswordOld(), request.getPasswordNew());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/logout")
    public String logout() {
        return "POST:: auth logout";
    }
}
