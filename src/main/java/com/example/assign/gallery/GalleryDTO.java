package com.example.assign.gallery;

import com.example.assign.product.ProductDTO;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GalleryDTO {
    private UUID id;
    private String thumbnail;
    private ProductDTO product;
}
