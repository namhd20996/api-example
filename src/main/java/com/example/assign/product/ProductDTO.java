package com.example.assign.product;

import com.example.assign.gallery.GalleryDTO;
import com.example.assign.util.BaseDTO;
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
public class ProductDTO extends BaseDTO {

    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
    private Integer quantity;
    private Double discount;
    private Integer status;
    private List<GalleryDTO> galleries = new ArrayList<>();
}
