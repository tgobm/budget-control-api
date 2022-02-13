package com.herokuapp.budgetcontrolapi.service.summary;

import com.herokuapp.budgetcontrolapi.domain.expense.Category;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;
import com.herokuapp.budgetcontrolapi.dto.revenue.response.RevenueResponse;
import com.herokuapp.budgetcontrolapi.dto.summary.SummaryResponse;
import com.herokuapp.budgetcontrolapi.service.expense.ExpenseService;
import com.herokuapp.budgetcontrolapi.service.revenue.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class SummaryServiceImpl implements SummaryService {

    private final RevenueService revenueService;

    private final ExpenseService expenseService;

    @Override
    public SummaryResponse summaryByMonth(Long year, Long month) {

        List<RevenueResponse> revenueResponses = revenueService.getAllRevenueByYearMonth(year, month);
        List<ExpenseResponse> expenseResponses = expenseService.getAllExpenseByYearMonth(year, month);

        BigDecimal sumRevenueMonth = BigDecimal.valueOf(revenueResponses.stream()
                .map(RevenueResponse::getValue).mapToDouble(BigDecimal::doubleValue).sum());

        BigDecimal sumExpenseMonth = BigDecimal.valueOf(expenseResponses.stream()
                .map(ExpenseResponse::getValue).mapToDouble(BigDecimal::doubleValue).sum());

        BigDecimal balanceByMonth = sumRevenueMonth.subtract(sumExpenseMonth);

        Map<Category, BigDecimal> sumByCategory = expenseResponses.stream()
                .collect(Collectors.toMap(ExpenseResponse::getCategory, ExpenseResponse::getValue, BigDecimal::add));

        return SummaryResponse.builder()
                .sumRevenueMonth(sumRevenueMonth)
                .sumExpenseMonth(sumExpenseMonth)
                .balanceByMonth(balanceByMonth)
                .sumByCategory(sumByCategory)
                .build();
    }
}
