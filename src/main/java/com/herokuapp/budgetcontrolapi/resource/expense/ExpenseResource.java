package com.herokuapp.budgetcontrolapi.resource.expense;

import com.herokuapp.budgetcontrolapi.domain.expense.Expense;
import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;
import com.herokuapp.budgetcontrolapi.service.expense.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin
@RequiredArgsConstructor
public class ExpenseResource {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest expense) {
        return new ResponseEntity<>(expenseService.createExpense(expense), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses() {
        return ResponseEntity.ok(expenseService.getExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable("id") Long id) {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }
}
