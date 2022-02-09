package com.herokuapp.budgetcontrolapi.dto.expense.mapper;

import com.herokuapp.budgetcontrolapi.domain.expense.Expense;
import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    Expense fromRequestToEntity(ExpenseRequest request);

    ExpenseResponse fromEntityToResponse(Expense entity);
}