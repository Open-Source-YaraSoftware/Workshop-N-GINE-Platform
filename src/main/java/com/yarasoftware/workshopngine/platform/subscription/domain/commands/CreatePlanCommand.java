package com.yarasoftware.workshopngine.platform.subscription.domain.commands;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.BillingCycle;

import java.util.List;

public record CreatePlanCommand(
        String name,
        String description,
        Integer price,
        Integer durationInMonths,
        BillingCycle billingCycle,
        List<String> limitations
) {}