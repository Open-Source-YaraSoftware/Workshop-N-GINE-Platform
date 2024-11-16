package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.SubscriptionStatus;

import java.time.LocalDateTime;

public record SubscriptionResource(
        Long id,
        Long planId,
        SubscriptionStatus status,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long workshopId
) {}