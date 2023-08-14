package com.example.assign.gallery;

import java.util.List;
import java.util.UUID;

public interface GalleryService {

    void addAllGallery(List<GalleryDTO> requests);

    void updateGallery(UUID uuid);
}
