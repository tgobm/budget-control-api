package com.herokuapp.budgetcontrolapi.dto.summary;

import com.herokuapp.budgetcontrolapi.domain.expense.Category;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Builder
@Data
public class SummaryResponse implements Serializable {

    private BigDecimal sumRevenueMonth;

    private BigDecimal sumExpenseMonth;

    private BigDecimal balanceByMonth;

    private Map<Category, BigDecimal> sumByCategory;
}
