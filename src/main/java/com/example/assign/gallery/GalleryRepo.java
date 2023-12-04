package com.example.assign.gallery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GalleryRepo extends JpaRepository<Gallery, UUID> {

    void deleteAllByProductId(UUID productId);
}
