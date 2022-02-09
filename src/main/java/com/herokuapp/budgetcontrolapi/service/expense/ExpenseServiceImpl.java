package com.herokuapp.budgetcontrolapi.service.expense;

import com.herokuapp.budgetcontrolapi.domain.expense.Expense;
import com.herokuapp.budgetcontrolapi.dto.expense.mapper.ExpenseMapper;
import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException;
import com.herokuapp.budgetcontrolapi.repository.expense.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseMapper expenseMapper;

    private final ExpenseRepository expenseRepository;

    @Override
    public ExpenseResponse createExpense(@Validated ExpenseRequest request) {
        Expense entity = expenseMapper.fromRequestToEntity(request);
        expenseRepository.save(entity);
        return expenseMapper.fromEntityToResponse(entity);
    }

    @Override
    public Expense getExpense(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense not found!"));
    }

    @Override
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }
}