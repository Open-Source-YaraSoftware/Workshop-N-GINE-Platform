package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.BillingCycle;

import java.util.List;

public record PlanResource(
        Long id,
        String name,
        String description,
        Integer price,
        Integer durationInMonths,
        BillingCycle billingCycle,
        List<String> limitations
) {}