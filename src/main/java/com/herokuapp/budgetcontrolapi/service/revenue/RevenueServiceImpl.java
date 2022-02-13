package com.herokuapp.budgetcontrolapi.service.revenue;

import com.herokuapp.budgetcontrolapi.domain.revenue.Revenue;
import com.herokuapp.budgetcontrolapi.dto.revenue.mapper.RevenueMapper;
import com.herokuapp.budgetcontrolapi.dto.revenue.request.RevenueRequest;
import com.herokuapp.budgetcontrolapi.dto.revenue.response.RevenueResponse;
import com.herokuapp.budgetcontrolapi.exception.ResourceNotFoundException;
import com.herokuapp.budgetcontrolapi.repository.revenue.RevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.herokuapp.budgetcontrolapi.util.ErrorMessage.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class RevenueServiceImpl implements RevenueService {

    private final RevenueMapper revenueMapper;

    private final RevenueRepository revenueRepository;

    @Override
    public RevenueResponse createRevenue(RevenueRequest request) {
        Revenue entity = revenueMapper.fromRequestToEntity(request);
        revenueRepository.save(entity);
        return revenueMapper.fromEntityToResponse(entity);
    }

    @Override
    public RevenueResponse getRevenue(Long id) {
        Revenue entity = revenueRepository.findById(id) //
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage()));
        return revenueMapper.fromEntityToResponse(entity);
    }

    @Override
    public List<RevenueResponse> getAllRevenueByDescription(String description) {
        List<RevenueResponse> responseList = new ArrayList<>();
        List<Revenue> entities = revenueRepository.findAll() //
                .stream() //
                .filter(revenue -> Objects.equals(revenue.getDescription(), description)) //
                .collect(Collectors.toList());
        entities.forEach(entity -> responseList.add(revenueMapper.fromEntityToResponse(entity)));
        return responseList;
    }

    @Override
    public List<RevenueResponse> getAllRevenueByYearMonth(Long year, Long month) {
        List<RevenueResponse> responseList = new ArrayList<>();
        List<Revenue> entities = revenueRepository.findAll() //
                .stream() //
                .filter(revenue -> Objects.equals(YearMonth.from(revenue.getDate()), YearMonth.of(year.intValue(), month.intValue()))) //
                .collect(Collectors.toList());
        entities.forEach(entity -> responseList.add(revenueMapper.fromEntityToResponse(entity)));
        return responseList;
    }

    @Override
    public RevenueResponse updateRevenue(RevenueRequest request, Long id) {
        Revenue returnedEntity = revenueRepository.findById(id) //
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage()));

        returnedEntity.setDescription(request.getDescription());
        returnedEntity.setValue(request.getValue());
        returnedEntity.setDate(request.getDate());
        revenueRepository.save(returnedEntity);
        return revenueMapper.fromEntityToResponse(returnedEntity);
    }

    @Override
    public void deleteRevenue(Long id) {
        Revenue entity = revenueRepository.findById(id) //
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND.getMessage()));
        revenueRepository.delete(entity);
    }
}