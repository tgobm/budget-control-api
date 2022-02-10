package com.herokuapp.budgetcontrolapi.service.revenue;

import com.herokuapp.budgetcontrolapi.domain.revenue.Revenue;
import com.herokuapp.budgetcontrolapi.dto.revenue.mapper.RevenueMapper;
import com.herokuapp.budgetcontrolapi.dto.revenue.request.RevenueRequest;
import com.herokuapp.budgetcontrolapi.dto.revenue.response.RevenueResponse;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException;
import com.herokuapp.budgetcontrolapi.repository.revenue.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class RevenueServiceImpl implements RevenueService {

    private final RevenueMapper revenueMapper;

    private final RevenueRepository revenueRepository;

    @Override
    public RevenueResponse createRevenue(@Validated RevenueRequest request) {
        Revenue entity = revenueMapper.fromRequestToEntity(request);
        revenueRepository.save(entity);
        return revenueMapper.fromEntityToResponse(entity);
    }

    @Override
    public Revenue getRevenue(Long id) {
        return revenueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Revenue not found!"));
    }

    @Override
    public List<Revenue> getRevenues() {
        return revenueRepository.findAll();
    }
}