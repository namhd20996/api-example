package com.example.assign.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegistrationRequest {

    @NotBlank
    @Pattern(message = "regex username not valid", regexp = "^[a-zA-Z\\d]+$")
    private String username;
    @NotNull
    @Pattern(
            message = "Regex password",
            regexp = "^.*(?=.{6,})(?=.+[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
    )
    private String password;
    @NotBlank(message = "Email not blank")
    @Pattern(message = "Regex email", regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]+?\\.[a-zA-Z]{2,3}$")
    private String email;

}
