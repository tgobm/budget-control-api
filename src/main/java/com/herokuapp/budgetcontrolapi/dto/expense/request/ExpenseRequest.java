package com.herokuapp.budgetcontrolapi.dto.expense.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseRequest implements Serializable {

    @NotEmpty
    private String description;

    @NotEmpty
    private BigDecimal value;

    @NotEmpty
    private Date date;
}