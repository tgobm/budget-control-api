package com.herokuapp.budgetcontrolapi.dto.expense.mapper;

import com.herokuapp.budgetcontrolapi.domain.expense.Expense;
import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    Expense fromRequestToEntity(ExpenseRequest request);

    ExpenseResponse fromEntityToResponse(Expense entity);
}
