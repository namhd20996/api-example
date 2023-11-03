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
@RequestMapping("/api/v2/category")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PreAuthorize("hasAnyAuthority(@adminCreate)")
    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO dto) {
        return new ResponseEntity<>(categoryService.addCategory(dto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority(@adminRead)")
    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDTO>> getAllStatus() {
        return new ResponseEntity<>(categoryService.findAllByStatus(1), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminUpdate)")
    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@Validated @RequestBody CategoryUpdateRequest request) {
        categoryService.updateCategory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminDelete)")
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
