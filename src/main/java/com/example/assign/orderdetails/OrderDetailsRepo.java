package com.example.assign.orderdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderDetailsRepo extends JpaRepository<com.example.assign.orderdetails.OrderDetails, UUID> {

    @Query("""
            SELECT o FROM OrderDetails o WHERE o.order.email = ?1
            """)
    List<com.example.assign.orderdetails.OrderDetails> findProductsByEmail(String email);
}
