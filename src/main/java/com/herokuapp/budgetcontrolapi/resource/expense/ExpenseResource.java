package com.herokuapp.budgetcontrolapi.resource.expense;

import com.herokuapp.budgetcontrolapi.dto.expense.request.ExpenseRequest;
import com.herokuapp.budgetcontrolapi.dto.expense.response.ExpenseResponse;
import com.herokuapp.budgetcontrolapi.service.expense.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin
@RequiredArgsConstructor
public class ExpenseResource {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody ExpenseRequest request) {
        return new ResponseEntity<>(expenseService.createExpense(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenseByDescription(@RequestParam String description) {
        return ResponseEntity.ok(expenseService.getAllExpenseByDescription(description));
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<ExpenseResponse>> getAllExpenseByYearMonth(@PathVariable Long year, @PathVariable Long month) {
        return ResponseEntity.ok(expenseService.getAllExpenseByYearMonth(year, month));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@Valid @RequestBody ExpenseRequest requestDetails, @PathVariable Long id) {
        return ResponseEntity.ok(expenseService.updateExpense(requestDetails, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}