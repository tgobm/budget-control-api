package com.herokuapp.budgetcontrolapi.resource.revenue;

import com.herokuapp.budgetcontrolapi.dto.revenue.request.RevenueRequest;
import com.herokuapp.budgetcontrolapi.dto.revenue.response.RevenueResponse;
import com.herokuapp.budgetcontrolapi.service.revenue.RevenueService;
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
@RequestMapping("/api/revenues")
@CrossOrigin
@RequiredArgsConstructor
public class RevenueResource {

    private final RevenueService revenueService;

    @PostMapping
    public ResponseEntity<RevenueResponse> createRevenue(@Valid @RequestBody RevenueRequest request) {
        return new ResponseEntity<>(revenueService.createRevenue(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RevenueResponse> getRevenue(@PathVariable Long id) {
        return ResponseEntity.ok(revenueService.getRevenue(id));
    }

    @GetMapping
    public ResponseEntity<List<RevenueResponse>> getAllRevenueByDescription(@RequestParam String description) {
        return ResponseEntity.ok(revenueService.getAllRevenueByDescription(description));
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<RevenueResponse>> getAllRevenueByYearMonth(@PathVariable Long year, @PathVariable Long month) {
        return ResponseEntity.ok(revenueService.getAllRevenueByYearMonth(year, month));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RevenueResponse> updateRevenue(@Valid @RequestBody RevenueRequest requestDetails, @PathVariable Long id) {
        return ResponseEntity.ok(revenueService.updateRevenue(requestDetails, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevenue(@PathVariable Long id) {
        revenueService.deleteRevenue(id);
        return ResponseEntity.noContent().build();
    }
}