package com.herokuapp.budgetcontrolapi.dto.revenue.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RevenueRequest implements Serializable {

    private String description;
    private BigDecimal value;
    private Date date;
}