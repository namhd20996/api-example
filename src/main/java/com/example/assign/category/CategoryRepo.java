package com.example.assign.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {

    Optional<Category> findCategoryByIdAndStatus(UUID uuid, Integer status);

    List<Category> findAllByStatus(Integer status);

    boolean existsByName(String name);
}
