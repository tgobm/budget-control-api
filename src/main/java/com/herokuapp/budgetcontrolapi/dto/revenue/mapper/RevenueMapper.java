package com.herokuapp.budgetcontrolapi.dto.revenue.mapper;

import com.herokuapp.budgetcontrolapi.domain.revenue.Revenue;
import com.herokuapp.budgetcontrolapi.dto.revenue.request.RevenueRequest;
import com.herokuapp.budgetcontrolapi.dto.revenue.response.RevenueResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RevenueMapper {

    Revenue fromRequestToEntity(RevenueRequest request);

    RevenueResponse fromEntityToResponse(Revenue entity);
}