package com.herokuapp.budgetcontrolapi.service.summary;

import com.herokuapp.budgetcontrolapi.dto.summary.SummaryResponse;

public interface SummaryService {

    SummaryResponse summaryByMonth(Long year, Long month);
}
