package com.herokuapp.budgetcontrolapi.resource.expense;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herokuapp.budgetcontrolapi.domain.expense.Expense;
import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.service.expense.ExpenseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ExpenseResource.class})
@ExtendWith(SpringExtension.class)
class ExpenseResourceTest {
    @Autowired
    private ExpenseResource expenseResource;

    @MockBean
    private ExpenseService expenseService;

    @Test
    void testGetExpenses() throws Exception {
        when(this.expenseService.getExpenses()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/expenses");

        MockMvcBuilders.standaloneSetup(this.expenseResource) //
                .build() //
                .perform(requestBuilder) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.content().contentType("application/json")) //
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetExpensesEncoding() throws Exception {
        when(this.expenseService.getExpenses()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/expenses");
        getResult.characterEncoding("Encoding");

        MockMvcBuilders.standaloneSetup(this.expenseResource).build() //
                .perform(getResult) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.content().contentType("application/json")) //
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testCreateExpense() throws Exception {
        when(this.expenseService.getExpenses()).thenReturn(new ArrayList<>());
        ExpenseRequest expenseRequest = new ExpenseRequest();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        expenseRequest.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        expenseRequest.setDescription("The characteristics of someone or something");
        expenseRequest.setValue(BigDecimal.valueOf(42L));
        String content = (new ObjectMapper()).writeValueAsString(expenseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/expenses") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(content);

        MockMvcBuilders.standaloneSetup(this.expenseResource).build() //
                .perform(requestBuilder) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.content().contentType("application/json")) //
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetExpense() throws Exception {
        Expense expense = new Expense();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        expense.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        expense.setDescription("The characteristics of someone or something");
        expense.setId(123L);
        expense.setValue(BigDecimal.valueOf(42L));
        when(this.expenseService.getExpense((Long) any())).thenReturn(expense);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/expenses/{id}", 123L);

        MockMvcBuilders.standaloneSetup(this.expenseResource) //
                .build() //
                .perform(requestBuilder) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.content().contentType("application/json")) //
                .andExpect(MockMvcResultMatchers.content() //
                        .string("{\"id\":123,\"description\":\"The characteristics of someone or something\",\"value\":42,\"date\":0}"));
    }
}

