package com.example.assign.orderdetail;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderDetailDTOMapper {

    private final ModelMapper mapper;

    public OrderDetail toEntity(OrderDetailDTO dto) {
        return Optional.ofNullable(dto)
                .map(orderDetails -> mapper.map(orderDetails, OrderDetail.class))
                .orElse(null);
    }

    public OrderDetailDTO toDTO(OrderDetail entity) {
        return Optional.ofNullable(entity)
                .map(orderDetails -> mapper.map(orderDetails, OrderDetailDTO.class))
                .orElse(null);
    }

    public OrderDetailResponse apply(OrderDetail request) {
        return new OrderDetailResponse(
                request.getId(),
                request.getOrder().getId(),
                request.getProduct().getName(),
                request.getProduct().getPrice(),
                request.getQuantity()
        );
    }

}
