package com.example.assign.order;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderDTOMapper {

    private final ModelMapper mapper;

    public Order toEntity(OrderDTO dto) {
        return Optional.ofNullable(dto)
                .map(order -> mapper.map(order, Order.class))
                .orElse(null);
    }

    public OrderDTO toDTO(Order entity) {
        return Optional.ofNullable(entity)
                .map(order -> mapper.map(order, OrderDTO.class))
                .orElse(null);
    }

    public List<OrderStatisticRevenue> orderStatisticRevenues(List<Object[]> requests) {
        return requests.stream()
                .map(o -> OrderStatisticRevenue.builder()
                        .day(String.valueOf(o[0]))
                        .month(String.valueOf(o[1]))
                        .totalMoney(Double.valueOf(o[2] + ""))
                        .build())
                .toList();
    }

}
