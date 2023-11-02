package com.example.assign.product;

import com.example.assign.category.CategoryDTO;
import com.example.assign.gallery.GalleryDTO;
import com.example.assign.util.BaseDTO;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductDTO extends BaseDTO {

    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
    @Min(value = 1)
    private Integer quantity;
    @Min(value = 1)
    private Double discount;
    private Integer status;
    private UUID categoryId;
    private String categoryName;
    private List<GalleryDTO> galleries = new ArrayList<>();
}
