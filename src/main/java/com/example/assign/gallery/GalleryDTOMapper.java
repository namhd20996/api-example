package com.example.assign.gallery;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GalleryDTOMapper {

    private final ModelMapper mapper;

    public Gallery toEntity(GalleryDTO dto) {
        return Optional.ofNullable(dto)
                .map(gallery -> mapper.map(gallery, Gallery.class))
                .orElse(null);
    }

    public GalleryDTO toDTO(Gallery entity) {
        return Optional.ofNullable(entity)
                .map(gallery -> mapper.map(gallery, GalleryDTO.class))
                .orElse(null);
    }

}
