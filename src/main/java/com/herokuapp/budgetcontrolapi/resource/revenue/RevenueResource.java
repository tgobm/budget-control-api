package com.herokuapp.budgetcontrolapi.resource.revenue;

import com.herokuapp.budgetcontrolapi.service.revenue.RevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/revenues")
@CrossOrigin
@RequiredArgsConstructor
public class RevenueResource {

    private final RevenueService revenueService;
}