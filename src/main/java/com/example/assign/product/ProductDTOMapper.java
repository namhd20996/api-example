package com.example.assign.product;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDTOMapper {

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

}
