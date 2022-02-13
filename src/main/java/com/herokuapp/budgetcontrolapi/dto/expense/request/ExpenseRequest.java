package com.herokuapp.budgetcontrolapi.dto.expense.request;

import com.herokuapp.budgetcontrolapi.domain.expense.Category;
import lombok.Data;
import org.mapstruct.EnumMapping;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ExpenseRequest implements Serializable {

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal value;

    @NotNull
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Category category;
}