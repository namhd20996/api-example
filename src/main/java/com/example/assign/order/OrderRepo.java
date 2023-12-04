package com.example.assign.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, UUID> {

    List<Order> findAllByIdAndStatus(UUID id, Integer status);

    List<Order> findOrdersByStatus(Integer status);

    @Query(value = """
            SELECT
                EXTRACT(DAY FROM order_date),EXTRACT(MONTH FROM order_date),SUM(total_money)
            FROM
                _order
            GROUP BY
                EXTRACT(DAY FROM order_date),EXTRACT(MONTH FROM order_date)
            ORDER BY
                EXTRACT(DAY FROM order_date)
            """, nativeQuery = true)
    List<Object[]> getRevenueByMonth();

}
