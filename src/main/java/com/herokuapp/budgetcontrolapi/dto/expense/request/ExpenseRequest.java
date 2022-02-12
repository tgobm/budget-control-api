package com.herokuapp.budgetcontrolapi.dto.expense.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseRequest implements Serializable {

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal value;

    @NotNull
    private Date date;
}