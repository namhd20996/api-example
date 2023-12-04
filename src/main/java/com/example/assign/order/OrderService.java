package com.example.assign.order;


import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> findAllByIdAndStatus(UUID id, Integer status);

    List<OrderDTO> findAll();

    OrderDTO findOrderById(UUID id);

    void addOrder(OrderAddRequest request);

    void updateOrder(OrderUpdateRequest request, UUID oid);

    void deleteOrder(UUID uuid);

    void approveOrder(UUID uuid);
    void shipOrder(UUID uuid);

    List<OrderDTO> findOrdersByStatus(Integer status);

    List<OrderStatisticRevenue> getRevenueByMonth();
}
