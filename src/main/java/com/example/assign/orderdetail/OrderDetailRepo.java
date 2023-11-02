package com.example.assign.orderdetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, UUID> {

    @Query("""
            SELECT o FROM OrderDetail o WHERE o.order.email = ?1
            """)
    List<OrderDetail> findProductsByEmail(String email);

    Optional<List<OrderDetail>> findAllByOrderId(UUID orderId);
}
