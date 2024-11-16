package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources;

public record SubscriptionResource(
        Long id,
        String membershipType,
        String startDate,
        String endDate,
        Float amount,
        Long workshopId
) {}