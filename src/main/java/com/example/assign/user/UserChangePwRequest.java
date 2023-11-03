package com.example.assign.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePwRequest {
    @NotNull
    @Pattern(
            message = "regex password",
            regexp = "^.*(?=.{6,})(?=.+[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
    )
    private String passwordOld;
    @NotNull
    @Pattern(
            message = "regex password",
            regexp = "^.*(?=.{6,})(?=.+[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$"
    )
    private String passwordNew;
}
