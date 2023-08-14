package com.example.assign.user;

import com.example.assign.role.RoleDTO;
import com.example.assign.util.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserDTO extends BaseDTO {
    private String username;
    private String email;
    private String phone;
    private String firstname;
    private String lastname;
    private String avatar;
    private String address;
    private String token;
    private Date dob;
    private Integer status;
    private List<RoleDTO> roles = new ArrayList<>();
}
