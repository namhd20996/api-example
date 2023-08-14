package com.example.assign.orderdetails;

import java.util.List;

public interface OrderDetailsService {

    void addAllOrderDetail(List<OrderDetails> dtoList);

    List<OrderDetailsDTO> findProductsByEmail(String email);
}
