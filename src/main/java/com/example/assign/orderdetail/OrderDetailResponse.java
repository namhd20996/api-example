package com.example.assign.orderdetail;

import java.util.UUID;

public record OrderDetailResponse(
        UUID orderDetailId,
        UUID orderId,
        String productName,
        Double price,
        Integer quantity
) {
}
