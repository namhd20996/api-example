package com.example.assign.orderdetail;

import com.example.assign.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepo orderDetailRepo;

    private final OrderDetailDTOMapper orderDetailMapper;

    @Override
    public void addAllOrderDetail(List<OrderDetail> requests) {
        orderDetailRepo.saveAll(requests);
    }

    @Override
    public List<OrderDetailDTO> findProductsByEmail(String email) {
        return orderDetailRepo.findProductsByEmail(email)
                .stream()
                .map(orderDetailMapper::toDTO)
                .toList();
    }

    @Override
    public List<OrderDetailResponse> findOrderDetailByOrderId(UUID oid) {
        List<OrderDetail> orderDetails = orderDetailRepo.findAllByOrderId(oid)
                .orElseThrow(() -> new ResourceNotFoundException("find order detail by id " + oid + " not found!"));

        return orderDetails.stream()
                .map(orderDetailMapper::apply)
                .toList();
    }
}
