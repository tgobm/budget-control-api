package com.herokuapp.budgetcontrolapi.dto.revenue.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RevenueResponse implements Serializable {

    private Long id;

    private String description;

    private BigDecimal value;

    private Date date;
}