package com.example.assign.product;

import com.example.assign.gallery.GalleryDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    private final ModelMapper mapper;

    public Product toEntity(ProductDTO dto) {
        return Optional.ofNullable(dto)
                .map(product -> mapper.map(product, Product.class))
                .orElse(null);
    }

    public ProductDTO toDTO(Product entity) {
        return Optional.ofNullable(entity)
                .map(product -> mapper.map(product, ProductDTO.class))
                .orElse(null);
    }

    public List<ProductStatisticalRevenue> toStatisticalRevenue(List<Object[]> requests) {
        return requests.stream()
                .map(o -> ProductStatisticalRevenue.builder()
                        .name(String.valueOf(o[0]))
                        .totalMoney(Double.valueOf(o[1] + ""))
                        .build())
                .toList();
    }

    @Override
    public ProductDTO apply(Product product) {
        List<GalleryDTO> galleryDTOS = product.getGalleries().stream()
                .map(item -> GalleryDTO.builder()
                        .id(item.getId())
                        .thumbnail(item.getThumbnail())
                        .build())
                .toList();
        return ProductDTO.builder()
                .id(product.getId())
                .createdDate(product.getCreatedDate())
                .createdBy(product.getCreatedBy())
                .lastModifiedDate(product.getLastModifiedDate())
                .lastModifiedBy(product.getLastModifiedBy())
                .name(product.getName())
                .shortDescription(product.getShortDescription())
                .longDescription(product.getLongDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .discount(product.getDiscount())
                .status(product.getStatus())
                .galleries(galleryDTOS)
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
