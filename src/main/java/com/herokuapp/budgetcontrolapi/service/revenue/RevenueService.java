package com.herokuapp.budgetcontrolapi.service.revenue;

import com.herokuapp.budgetcontrolapi.domain.revenue.Revenue;
import com.herokuapp.budgetcontrolapi.dto.revenue.request.RevenueRequest;
import com.herokuapp.budgetcontrolapi.dto.revenue.response.RevenueResponse;

import java.util.List;

public interface RevenueService {

    RevenueResponse createRevenue(RevenueRequest request);

    Revenue getRevenue(Long id);

    List<Revenue> getRevenues();
}