package com.herokuapp.budgetcontrolapi.service.expense;

import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse createExpense(ExpenseRequest request);

    ExpenseResponse getExpense(Long id);

    List<ExpenseResponse> getAllExpenseByDescription(String description);

    List<ExpenseResponse> getAllExpenseByYearMonth(Long year, Long month);

    ExpenseResponse updateExpense(ExpenseRequest requestDetails, Long id);

    void deleteExpense(Long id);
}