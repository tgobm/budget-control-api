package com.herokuapp.budgetcontrolapi.resource.summary;

import com.herokuapp.budgetcontrolapi.dto.summary.response.SummaryResponse;
import com.herokuapp.budgetcontrolapi.service.summary.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summaries")
@CrossOrigin
@RequiredArgsConstructor
public class SummaryResource {

    private final SummaryService summaryService;

    @GetMapping("/{year}/{month}")
    public ResponseEntity<SummaryResponse> summaryByYearMonth(@PathVariable Long year, @PathVariable Long month) {
        return ResponseEntity.ok(summaryService.summaryByYearMonth(year, month));
    }
}