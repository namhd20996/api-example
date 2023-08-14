package com.example.assign.orderdetails;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderDetailsDTOMapper {

    private final ModelMapper mapper;

    public OrderDetails toEntity(OrderDetailsDTO dto) {
        return Optional.ofNullable(dto)
                .map(orderDetails -> mapper.map(orderDetails, OrderDetails.class))
                .orElse(null);
    }

    public OrderDetailsDTO toDTO(OrderDetails entity) {
        return Optional.ofNullable(entity)
                .map(orderDetails -> mapper.map(orderDetails, OrderDetailsDTO.class))
                .orElse(null);
    }

}
