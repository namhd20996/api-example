package com.example.assign.orderdetail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order-detail")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("/get-email")
    public ResponseEntity<List<OrderDetailDTO>> getAllByEmail(
            @RequestParam("email") String email
    ) {
        return new ResponseEntity<>(orderDetailService.findProductsByEmail(email), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:read')")
    @GetMapping("{oid}")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetailByOrderId(
            @PathVariable("oid") UUID oid
    ) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailByOrderId(oid), HttpStatus.OK);
    }
}
