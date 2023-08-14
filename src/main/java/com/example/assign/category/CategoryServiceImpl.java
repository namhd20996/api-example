package com.example.assign.category;

import com.example.assign.exception.ApiRequestException;
import com.example.assign.exception.ResourceDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final CategoryDTOMapper converter;

    @Override
    public CategoryDTO addCategory(CategoryDTO dto) {
        boolean existsByName = existsByName(dto.getName());
        if (existsByName) {
            throw new ResourceDuplicateException("name category exists");
        }
        Category category = converter.toEntity(dto);
        category.setStatus(1);
        return converter.toDTO(categoryRepo.save(category));
    }

    @Override
    public List<CategoryDTO> findAllCategory() {
        return categoryRepo.findAll()
                .stream()
                .map(converter::toDTO)
                .toList();
    }

    @Override
    public CategoryDTO findOneById(UUID id) {
        return categoryRepo.findById(id)
                .map(converter::toDTO)
                .orElseThrow(() -> new ApiRequestException("Category id: " + id + "not found!.."));
    }

    @Override
    public CategoryDTO findCategoryByIdAndStatus(UUID id, Integer status) {
        return categoryRepo.findCategoryByIdAndStatus(id, status)
                .map(converter::toDTO)
                .orElseThrow(() -> new ApiRequestException("Category id: " + id + " not found!.."));
    }

    @Override
    public List<CategoryDTO> findAllByStatus(Integer status) {
        return categoryRepo.findAllByStatus(status)
                .stream()
                .map(converter::toDTO)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepo.existsByName(name);
    }
}
