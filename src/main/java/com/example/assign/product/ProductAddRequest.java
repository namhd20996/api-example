package com.example.assign.product;

import com.example.assign.category.CategoryDTO;
import com.example.assign.gallery.GalleryDTO;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductAddRequest {
    @NotBlank
    private String name;

    private String shortDescription;

    private String longDescription;
    @NotNull
    @Min(message = "price min = 0", value = 0)
    private Double price;
    @Min(message = "quantity min = 0", value = 0)
    private Integer quantity;
    @Min(message = "discount min = 0", value = 0)
    private Double discount;

    private CategoryDTO category;

    private List<GalleryDTO> galleries;
}
