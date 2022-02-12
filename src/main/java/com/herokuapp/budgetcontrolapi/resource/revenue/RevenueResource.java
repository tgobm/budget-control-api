package com.herokuapp.budgetcontrolapi.resource.revenue;

import com.herokuapp.budgetcontrolapi.domain.revenue.Revenue;
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
    public ResponseEntity<RevenueResponse> createRevenue(@Valid @RequestBody RevenueRequest revenue) {
        return new ResponseEntity<>(revenueService.createRevenue(revenue), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Revenue>> getRevenues() {
        return ResponseEntity.ok(revenueService.getAllRevenues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Revenue> getRevenue(@PathVariable("id") Long id) {
        return ResponseEntity.ok(revenueService.getRevenue(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Revenue> updateRevenue(@Valid @RequestBody RevenueRequest requestDetails, @PathVariable("id") Long id) {
        Revenue entity = revenueService.updateRevenue(requestDetails, id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") Long id) {
        revenueService.deleteRevenue(id);
        return ResponseEntity.noContent().build();
    }
}