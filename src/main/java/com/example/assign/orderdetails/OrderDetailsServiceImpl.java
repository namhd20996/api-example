package com.example.assign.orderdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepo orderDetailsRepo;

    private final OrderDetailsDTOMapper orderDetailsConverter;

    @Override
    public void addAllOrderDetail(List<OrderDetails> requests) {
        orderDetailsRepo.saveAll(requests);
    }

    @Override
    public List<OrderDetailsDTO> findProductsByEmail(String email) {
        return orderDetailsRepo.findProductsByEmail(email)
                .stream()
                .map(orderDetailsConverter::toDTO)
                .toList();
    }
}
