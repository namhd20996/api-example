package com.example.assign.orderdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @PostMapping("/get-email")
    public ResponseEntity<List<OrderDetailsDTO>> getAllByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(orderDetailsService.findProductsByEmail(email), HttpStatus.OK);
    }
}
