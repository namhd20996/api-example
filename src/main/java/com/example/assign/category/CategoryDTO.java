package com.example.assign.category;

import com.example.assign.product.ProductDTO;
import com.example.assign.util.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CategoryDTO extends BaseDTO {

    private String name;
    private String description;
    private String image;
    private Integer status;
}
