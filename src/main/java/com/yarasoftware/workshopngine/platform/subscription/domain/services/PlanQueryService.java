package com.yarasoftware.workshopngine.platform.subscription.domain.services;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetPlanByIdQuery;

import java.util.List;

public interface PlanQueryService {
    List<Plan> getPaymentHistory(GetPlanByIdQuery query);
    List<Plan> getAllPayments();
}