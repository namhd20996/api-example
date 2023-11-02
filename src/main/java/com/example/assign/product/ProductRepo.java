package com.example.assign.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {

    Optional<Product> findProductByIdAndStatus(UUID uuid, Integer status);

    @Query("""
                SELECT o FROM Product  o WHERE LOWER(o.name) LIKE LOWER(CONCAT(?1, '%') ) 
            """)
    Optional<List<Product>> findProductsByNameStartingWithIngoreCase(String name);

    boolean existsByName(String name);

    @Query(""" 
            SELECT o FROM Product o WHERE o.category.id = ?1
            """)
    Optional<List<Product>> findAllByCategoryId(UUID id);

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Product o SET o.quantity=?1 WHERE o.id=?2 AND o.status=?3
            """)
    void updateQuantityByIdAndStatus(Integer quantity, UUID id, Integer status);

    List<Product> findProductsByStatus(Integer status, Sort sort);

    @Query(value = """
                SELECT c.name, SUM(d.total_money) FROM _category c
                JOIN _product p ON p.category_id = c.id
                JOIN _orderdetails d ON d.product_id = p.id
                GROUP BY c.id
            """, nativeQuery = true)
    List<Object[]> findAllRevenueByCategory();

    @Query("""
                SELECT COUNT(o) FROM Product o
            """)
    Integer countAll();

}
