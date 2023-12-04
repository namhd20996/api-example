package com.example.assign.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> get(
            @RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "bestSale", required = false) String bestSale,
            @RequestParam(value = "popular", required = false) String popular
    ) {
        return new ResponseEntity<>(productService.findAllProduct(price, bestSale, popular), HttpStatus.OK);
    }

    @GetMapping("/all/manager/{page}/{limit}")
    public ResponseEntity<ProductResponse> getProducts(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit
    ) {
        return new ResponseEntity<>(productService.findAllProduct(page, limit), HttpStatus.OK);
    }

    @GetMapping("/all/name")
    public ResponseEntity<List<ProductDTO>> getProductsByName(
            @RequestParam("name") String name
    ) {
        return new ResponseEntity<>(productService.findProductsByName(name), HttpStatus.OK);
    }

    @GetMapping("/all/category")
    public ResponseEntity<List<ProductDTO>> getAllByCategory(
            @RequestParam("id") UUID id
    ) {
        return new ResponseEntity<>(productService.findAllByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/get-id")
    public ResponseEntity<ProductDTO> getProductById(
            @RequestParam("id") UUID id
    ) {
        return new ResponseEntity<>(productService.findOneProductById(id), HttpStatus.OK);
    }

}
