package com.herokuapp.budgetcontrolapi.service.summary;

import com.herokuapp.budgetcontrolapi.dto.summary.response.SummaryResponse;

public interface SummaryService {

    SummaryResponse summaryByYearMonth(Long year, Long month);
}
