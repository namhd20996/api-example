package com.example.assign.orderdetail;

import com.example.assign.order.OrderDTO;
import com.example.assign.product.ProductDTO;
import com.example.assign.util.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class OrderDetailDTO extends BaseDTO {

    private Double price;
    private Integer quantity;
    private Double totalMoney;
    private ProductDTO product;
    private OrderDTO order;
}
