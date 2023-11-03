package com.example.assign.orderdetail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/order-detail")
@RequiredArgsConstructor
public class OrderDetailAdminController {

    private final OrderDetailService orderDetailService;

    @PreAuthorize("hasAnyAuthority(@adminRead)")
    @GetMapping("{oid}")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetailByOrderId(@PathVariable("oid") UUID oid) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailByOrderId(oid), HttpStatus.OK);
    }
}
