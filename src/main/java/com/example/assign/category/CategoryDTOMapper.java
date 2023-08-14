package com.example.assign.category;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryDTOMapper {

    private final ModelMapper mapper;

    public Category toEntity(CategoryDTO dto) {
        return Optional.ofNullable(dto)
                .map(category -> mapper.map(category, Category.class))
                .orElse(null);
    }

    public CategoryDTO toDTO(Category entity) {
        return Optional.ofNullable(entity)
                .map(category -> mapper.map(category, CategoryDTO.class))
                .orElse(null);
    }

}
