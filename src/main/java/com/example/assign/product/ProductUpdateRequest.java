package com.example.assign.product;

import com.example.assign.gallery.GalleryUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateRequest {
    private UUID id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
    private Integer quantity;
    private Double discount;
    private List<GalleryUpdateRequest> galleries;
}
