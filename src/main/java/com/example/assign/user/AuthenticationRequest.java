package com.example.assign.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotBlank
    @Pattern(message = "regex username not valid", regexp = "^[a-zA-Z\\d]+$")
    private String username;
    @NotNull
    @Pattern(
            message = "Regex password",
            regexp = "^.*(?=.{6,})(?=.+[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
    )
    private String password;
}
