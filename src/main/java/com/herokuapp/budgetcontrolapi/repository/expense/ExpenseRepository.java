package com.herokuapp.budgetcontrolapi.repository.expense;

import com.herokuapp.budgetcontrolapi.domain.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}