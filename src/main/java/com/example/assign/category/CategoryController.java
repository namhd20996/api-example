package com.example.assign.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("hasAnyAuthority('admin:read')")
    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO dto) {
        return new ResponseEntity<>(categoryService.addCategory(dto), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDTO>> getAllStatus() {
        return new ResponseEntity<>(categoryService.findAllByStatus(1), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:update')")
    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@Validated @RequestBody CategoryUpdateRequest request) {
        categoryService.updateCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:delete')")
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
