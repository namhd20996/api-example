package com.example.assign.product;

import com.example.assign.validation.ValidationHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    private final ValidationHandle validationHandle;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> get(
            @RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "bestSale", required = false) String bestSale,
            @RequestParam(value = "popular", required = false) String popular
    ) {
        return new ResponseEntity<>(productService.findAllProduct(price, bestSale, popular), HttpStatus.OK);
    }

    @GetMapping("/all/manager/{page}/{limit}")
    public ResponseEntity<ProductResponse> getProducts(@PathVariable("page") Integer page,
                                                       @PathVariable("limit") Integer limit) {
        return new ResponseEntity<>(productService.findAllProduct(page, limit), HttpStatus.OK);
    }

    @GetMapping("/all/name")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@RequestParam("name") String name
    ) {
        return new ResponseEntity<>(productService.findProductsByName(name), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyAuthority('admin:read')")
//    @GetMapping("/all/{status}")
//    public ResponseEntity<List<ProductDTO>> getProductsByStatus(@PathVariable("status") Integer status) {
//        return new ResponseEntity<>(productService.findProductsByStatus(status), HttpStatus.OK);
//    }

    @GetMapping("/all/category")
    public ResponseEntity<List<ProductDTO>> getAllByCategory(@RequestParam("id") UUID id) {
        return new ResponseEntity<>(productService.findAllByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/get-id")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam("id") UUID id) {
        return new ResponseEntity<>(productService.findOneProductById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:read')")
    @PostMapping("/add/{uuid}")
    public ResponseEntity<?> post(
            @PathVariable("uuid") UUID uuid,
            @Validated @RequestBody ProductAddRequest request,
            Errors errors
    ) {
        validationHandle.handleValidate(errors);
        productService.addProduct(uuid, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('admin:read')")
    @PutMapping("/update/{cid}")
    public ResponseEntity<?> put(
            @Validated @RequestBody ProductUpdateRequest request,
            @PathVariable("cid") UUID cid,
            Errors errors
    ) {
        validationHandle.handleValidate(errors);
        productService.updateProduct(request, cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:read')")
    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        productService.deleteProduct(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:read')")
    @GetMapping("/statistic/category")
    public ResponseEntity<List<ProductStatisticalRevenue>> statisticByCategory() {
        return new ResponseEntity<>(productService.findAllRevenueByCategory(), HttpStatus.OK);
    }

}
