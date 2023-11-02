package com.example.assign.category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO addCategory(CategoryDTO dto);

    void updateCategory(CategoryUpdateRequest request);

    void deleteCategory(UUID categoryId);

    List<CategoryDTO> findAllCategory();

    CategoryDTO findOneById(UUID id);

    CategoryDTO findCategoryByIdAndStatus(UUID uuid, Integer status);

    List<CategoryDTO> findAllByStatus(Integer status);

    boolean existsByName(String name);

}
