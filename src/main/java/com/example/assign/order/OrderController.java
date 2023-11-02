package com.example.assign.order;

import com.example.assign.validation.ValidationHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final ValidationHandle validationHandle;

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@Validated @RequestBody OrderAddRequest request, Errors errors) {
        validationHandle.handleValidate(errors);
        orderService.addOrder(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/revenue/month")
    public ResponseEntity<List<OrderStatisticRevenue>> getRevenueByMonth() {
        return new ResponseEntity<>(orderService.getRevenueByMonth(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:read')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrder() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:update')")
    @PutMapping("/update/{oid}")
    public ResponseEntity<?> updateOrder(
            @Validated @RequestBody OrderUpdateRequest request,
            @PathVariable("oid") UUID oid,
            Errors errors
    ) {
        validationHandle.handleValidate(errors);
        orderService.updateOrder(request, oid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:delete')")
    @DeleteMapping("/delete/{oid}")
    public ResponseEntity<?> deleteOrder(@PathVariable("oid") UUID oid) {
        orderService.deleteOrder(oid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:update')")
    @PutMapping("/approve/{oid}")
    public ResponseEntity<?> approveOrder(@PathVariable("oid") UUID oid) {
        orderService.approveOrder(oid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('admin:update')")
    @PutMapping("/ship/{oid}")
    public ResponseEntity<?> shipOrder(@PathVariable("oid") UUID oid) {
        orderService.shipOrder(oid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
