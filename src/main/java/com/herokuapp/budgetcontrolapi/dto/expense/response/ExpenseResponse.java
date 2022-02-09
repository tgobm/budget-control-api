package com.herokuapp.budgetcontrolapi.dto.expense.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseResponse implements Serializable {

    private Long id;

    private String description;

    private BigDecimal value;

    private Date date;
}