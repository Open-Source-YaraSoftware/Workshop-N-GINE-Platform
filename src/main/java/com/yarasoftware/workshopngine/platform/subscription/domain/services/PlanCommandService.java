package com.yarasoftware.workshopngine.platform.subscription.domain.services;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreatePlanCommand;

public interface PlanCommandService {
    Long createPlan(CreatePlanCommand command);
    void updatePlan(Long planId, CreatePlanCommand command);
    void deletePlan(Long planId);
}