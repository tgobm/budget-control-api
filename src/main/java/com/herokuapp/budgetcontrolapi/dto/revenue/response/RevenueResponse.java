package com.herokuapp.budgetcontrolapi.dto.revenue.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RevenueResponse implements Serializable {

    private Long id;

    private String description;

    private BigDecimal value;

    private LocalDate date;
}