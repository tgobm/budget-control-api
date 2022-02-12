package com.herokuapp.budgetcontrolapi.service.expense;

import com.herokuapp.budgetcontrolapi.domain.expense.Expense;
import com.herokuapp.budgetcontrolapi.dto.expense.mapper.ExpenseMapper;
import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException;
import com.herokuapp.budgetcontrolapi.repository.expense.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.herokuapp.budgetcontrolapi.util.ErrorMessage.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseMapper expenseMapper;

    private final ExpenseRepository expenseRepository;

    @Override
    public ExpenseResponse createExpense(ExpenseRequest request) {
        Expense entity = expenseMapper.fromRequestToEntity(request);
        expenseRepository.save(entity);
        return expenseMapper.fromEntityToResponse(entity);
    }

    @Override
    public ExpenseResponse getExpense(Long id) {
        Expense entity = expenseRepository.findById(id) //
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage()));
        return expenseMapper.fromEntityToResponse(entity);
    }

    @Override
    public List<ExpenseResponse> getAllExpenses() {
        List<ExpenseResponse> responseList = new ArrayList<>();
        List<Expense> entities = expenseRepository.findAll();
        entities.forEach(entity -> responseList.add(expenseMapper.fromEntityToResponse(entity)));
        return responseList;
    }

    @Override
    public ExpenseResponse updateExpense(ExpenseRequest requestDetails, Long id) {
        Expense returnedEntity = expenseRepository.findById(id) //
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage()));

        returnedEntity.setDescription(requestDetails.getDescription());
        returnedEntity.setValue(requestDetails.getValue());
        returnedEntity.setDate(requestDetails.getDate());

        expenseRepository.save(returnedEntity);
        return expenseMapper.fromEntityToResponse(returnedEntity);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense entity = expenseRepository.findById(id) //
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage()));
        expenseRepository.delete(entity);
    }
}