package com.example.assign.product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    void addProduct(UUID uuid, ProductAddRequest request);

    boolean existsByName(String name);

    List<ProductDTO> findAllProduct();

    ProductResponse findAllProduct(Integer page, Integer limit);

    ProductDTO findOneProductById(UUID id);

    ProductDTO findProductByIdAndStatus(UUID uuid, Integer status);

    List<ProductDTO> findAllByCategoryId(UUID id);

    void updateQuantityByIdAndStatus(Integer quantity, UUID id, Integer status);

    List<ProductDTO> findProductsByStatus(Integer status);

    void deleteProduct(UUID uuid);

    List<ProductStatisticalRevenue> findAllRevenueByCategory();

    Integer count();
}
