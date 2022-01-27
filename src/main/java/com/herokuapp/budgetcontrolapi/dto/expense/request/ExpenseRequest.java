package com.herokuapp.budgetcontrolapi.dto.expense.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseRequest implements Serializable {

    private String description;
    private BigDecimal value;
    private Date date;
}
