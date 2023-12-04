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
@RequestMapping("/api/v2/order")
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderService orderService;

    private final ValidationHandle validationHandle;

    @PreAuthorize("hasAnyAuthority(@adminRead)")
    @GetMapping("/revenue/month")
    public ResponseEntity<List<OrderStatisticRevenue>> getRevenueByMonth() {
        return new ResponseEntity<>(orderService.getRevenueByMonth(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminRead)")
    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrder() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminUpdate)")
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

    @PreAuthorize("hasAnyAuthority(@adminDelete)")
    @DeleteMapping("/delete/{oid}")
    public ResponseEntity<?> deleteOrder(@PathVariable("oid") UUID oid) {
        orderService.deleteOrder(oid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminUpdate)")
    @PutMapping("/approve/{oid}")
    public ResponseEntity<?> approveOrder(@PathVariable("oid") UUID oid) {
        orderService.approveOrder(oid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(@adminUpdate)")
    @PutMapping("/ship/{oid}")
    public ResponseEntity<?> shipOrder(@PathVariable("oid") UUID oid) {
        orderService.shipOrder(oid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
