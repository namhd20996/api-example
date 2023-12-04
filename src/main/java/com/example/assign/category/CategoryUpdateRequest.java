package com.example.assign.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CategoryUpdateRequest {

    private UUID id;

    private String name;

    private String image;

    private String description;
}
