package com.herokuapp.budgetcontrolapi.service.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.herokuapp.budgetcontrolapi.domain.expense.Expense;
import com.herokuapp.budgetcontrolapi.dto.expense.mapper.ExpenseMapper;
import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException;
import com.herokuapp.budgetcontrolapi.repository.expense.ExpenseRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ExpenseServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ExpenseServiceImplTest {
    @MockBean
    private ExpenseMapper expenseMapper;

    @MockBean
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseServiceImpl expenseServiceImpl;

    @Test
    void testCreateExpense() {
        Expense expense = new Expense();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        expense.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        expense.setDescription("The characteristics of someone or something");
        expense.setId(123L);
        expense.setValue(BigDecimal.valueOf(42L));
        when(this.expenseRepository.save((Expense) any())).thenReturn(expense);

        Expense expense1 = new Expense();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        expense1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        expense1.setDescription("The characteristics of someone or something");
        expense1.setId(123L);
        expense1.setValue(BigDecimal.valueOf(42L));

        ExpenseResponse expenseResponse = new ExpenseResponse();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        expenseResponse.setDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        expenseResponse.setDescription("The characteristics of someone or something");
        expenseResponse.setId(123L);
        expenseResponse.setValue(BigDecimal.valueOf(42L));
        when(this.expenseMapper.fromEntityToResponse((Expense) any())).thenReturn(expenseResponse);
        when(this.expenseMapper.fromRequestToEntity((ExpenseRequest) any())).thenReturn(expense1);

        ExpenseRequest expenseRequest = new ExpenseRequest();
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        expenseRequest.setDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        expenseRequest.setDescription("The characteristics of someone or something");
        expenseRequest.setValue(BigDecimal.valueOf(42L));
        ExpenseResponse actualCreateExpenseResult = this.expenseServiceImpl.createExpense(expenseRequest);
        assertSame(expenseResponse, actualCreateExpenseResult);
        assertEquals("42", actualCreateExpenseResult.getValue().toString());
        verify(this.expenseRepository).save((Expense) any());
        verify(this.expenseMapper).fromEntityToResponse((Expense) any());
        verify(this.expenseMapper).fromRequestToEntity((ExpenseRequest) any());
        assertTrue(this.expenseServiceImpl.getExpenses().isEmpty());
    }

    @Test
    void testCreateExpense2() {
        Expense expense = new Expense();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        expense.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        expense.setDescription("The characteristics of someone or something");
        expense.setId(123L);
        expense.setValue(BigDecimal.valueOf(42L));
        when(this.expenseRepository.save((Expense) any())).thenReturn(expense);

        Expense expense1 = new Expense();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        expense1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        expense1.setDescription("The characteristics of someone or something");
        expense1.setId(123L);
        expense1.setValue(BigDecimal.valueOf(42L));
        when(this.expenseMapper.fromEntityToResponse((Expense) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        when(this.expenseMapper.fromRequestToEntity((ExpenseRequest) any())).thenReturn(expense1);

        ExpenseRequest expenseRequest = new ExpenseRequest();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        expenseRequest.setDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        expenseRequest.setDescription("The characteristics of someone or something");
        expenseRequest.setValue(BigDecimal.valueOf(42L));
        assertThrows(ResourceNotFoundException.class, () -> this.expenseServiceImpl.createExpense(expenseRequest));
        verify(this.expenseRepository).save((Expense) any());
        verify(this.expenseMapper).fromEntityToResponse((Expense) any());
        verify(this.expenseMapper).fromRequestToEntity((ExpenseRequest) any());
    }

    @Test
    void testGetExpense() {
        Expense expense = new Expense();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        expense.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        expense.setDescription("The characteristics of someone or something");
        expense.setId(123L);
        expense.setValue(BigDecimal.valueOf(42L));
        Optional<Expense> ofResult = Optional.of(expense);
        when(this.expenseRepository.findById((Long) any())).thenReturn(ofResult);
        Expense actualExpense = this.expenseServiceImpl.getExpense(123L);
        assertSame(expense, actualExpense);
        assertEquals("42", actualExpense.getValue().toString());
        verify(this.expenseRepository).findById((Long) any());
        assertTrue(this.expenseServiceImpl.getExpenses().isEmpty());
    }

    @Test
    void testGetExpense2() {
        when(this.expenseRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.expenseServiceImpl.getExpense(123L));
        verify(this.expenseRepository).findById((Long) any());
    }

    @Test
    void testGetExpense3() {
        when(this.expenseRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> this.expenseServiceImpl.getExpense(123L));
        verify(this.expenseRepository).findById((Long) any());
    }

    @Test
    void testGetExpenses() {
        ArrayList<Expense> expenseList = new ArrayList<>();
        when(this.expenseRepository.findAll()).thenReturn(expenseList);
        List<Expense> actualExpenses = this.expenseServiceImpl.getExpenses();
        assertSame(expenseList, actualExpenses);
        assertTrue(actualExpenses.isEmpty());
        verify(this.expenseRepository).findAll();
    }

    @Test
    void testGetExpenses2() {
        when(this.expenseRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.expenseServiceImpl.getExpenses());
        verify(this.expenseRepository).findAll();
    }
}

