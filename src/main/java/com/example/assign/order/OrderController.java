package com.example.assign.order;

import com.example.assign.validation.ValidationHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
}
