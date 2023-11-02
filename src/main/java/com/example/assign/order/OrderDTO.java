package com.example.assign.order;

import com.example.assign.util.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class OrderDTO extends BaseDTO {

    private Date orderDate;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private Integer status;
    private Double totalMoney;
}
