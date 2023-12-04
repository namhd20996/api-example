package com.example.assign.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderStatisticRevenue {

    private String day;

    private String month;

    private Double totalMoney;
}
