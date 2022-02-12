package com.herokuapp.budgetcontrolapi.service.revenue;

import com.herokuapp.budgetcontrolapi.dto.revenue.request.RevenueRequest;
import com.herokuapp.budgetcontrolapi.dto.revenue.response.RevenueResponse;

import java.util.List;

public interface RevenueService {

    RevenueResponse createRevenue(RevenueRequest request);

    RevenueResponse getRevenue(Long id);

    List<RevenueResponse> getAllRevenues();

    RevenueResponse updateRevenue(RevenueRequest requestDetails, Long id);

    void deleteRevenue(Long id);
}