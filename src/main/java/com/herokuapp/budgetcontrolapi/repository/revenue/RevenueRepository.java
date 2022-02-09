package com.herokuapp.budgetcontrolapi.repository.revenue;

import com.herokuapp.budgetcontrolapi.domain.revenue.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}