package com.herokuapp.budgetcontrolapi.domain.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExpenseTest {
    @Test
    void testConstructor() {
        assertEquals("{\"date\":null,\"description\":null,\"id\":null,\"value\":null}", (new Expense()).toString());
    }
}

