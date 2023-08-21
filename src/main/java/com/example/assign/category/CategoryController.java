package com.example.assign.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO dto) {
        return new ResponseEntity<>(categoryService.addCategory(dto), HttpStatus.CREATED);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDTO>> getAllStatus() {
        return new ResponseEntity<>(categoryService.findAllByStatus(1), HttpStatus.OK);
    }
}
