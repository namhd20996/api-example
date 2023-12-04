package com.example.assign.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateRequest {
    private String username;
    @Pattern(
            message = "regex firstname not valid",
            regexp = "^[a-zA-Z\\s]+$"
    )
    private String firstname;
    @Pattern(
            message = "regex lastname not valid",
            regexp = "^[a-zA-Z\\s]+$"
    )
    private String lastname;
    @NotBlank(message = "Email not blank")
    @Pattern(
            message = "regex email not valid",
            regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]+?\\.[a-zA-Z]{2,3}$"
    )
    private String email;
    //    @Pattern(
//            message = "regex date",
//            regexp = "yyyy-MM-dd"
//    )
    private Date dob;
    @Pattern(
            message = "regex phone not valid",
            regexp = "^(0?)(3[2-9]|5[6|8]|7[0|6-9]|8[0-6|]|9[0-4|6-9])[0-9]{7}$"
    )
    private String phone;

    private String address;

    private String avatar;

    private Integer status;

}
