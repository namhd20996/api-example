package com.example.assign.role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleAddRequest {

    private String name;

    private String code;
}
