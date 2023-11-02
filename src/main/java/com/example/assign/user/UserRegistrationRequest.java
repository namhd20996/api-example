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
    @Pattern(message = "Regex email", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private String email;

}
