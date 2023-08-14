package com.example.assign.gallery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepo galleryRepo;

    private final GalleryDTOMapper galleryDTOMapper;

    @Override
    public void addAllGallery(List<GalleryDTO> requests) {
        List<Gallery> galleries = requests.stream()
                .map(galleryDTOMapper::toEntity)
                .toList();
        galleryRepo.saveAll(galleries);
    }

    @Override
    public void updateGallery(UUID uuid) {

    }
}
