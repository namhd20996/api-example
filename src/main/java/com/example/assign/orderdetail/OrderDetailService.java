package com.example.assign.orderdetail;

import java.util.List;
import java.util.UUID;

public interface OrderDetailService {

    void addAllOrderDetail(List<OrderDetail> dtoList);

    List<OrderDetailDTO> findProductsByEmail(String email);

    List<OrderDetailResponse> findOrderDetailByOrderId(UUID oid);
}
