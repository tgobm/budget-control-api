package com.herokuapp.budgetcontrolapi.dto.revenue.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RevenueRequest implements Serializable {

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal value;

    @NotNull
    private LocalDate date;
}