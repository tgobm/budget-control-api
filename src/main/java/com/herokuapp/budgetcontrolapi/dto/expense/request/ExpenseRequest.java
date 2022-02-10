package com.herokuapp.budgetcontrolapi.dto.expense.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseRequest implements Serializable {

    @NotBlank(message = "field does not accept null or empty value")
    private String description;

    @NotNull(message = "field cannot be null")
    private BigDecimal value;

    @NotNull(message = "field cannot be null")
    private Date date;
}