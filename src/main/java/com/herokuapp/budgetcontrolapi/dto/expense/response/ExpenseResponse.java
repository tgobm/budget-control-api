package com.herokuapp.budgetcontrolapi.dto.expense.response;

import com.herokuapp.budgetcontrolapi.domain.expense.Category;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseResponse implements Serializable {

    private Long id;

    private String description;

    private BigDecimal value;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Category category;
}