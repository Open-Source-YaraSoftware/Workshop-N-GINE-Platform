package com.yarasoftware.workshopngine.platform.subscription.domain.commands;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.SubscriptionStatus;

public record CreateSubscriptionCommand(
        SubscriptionStatus type,
        Float amount,
        Long workshopId,
        String paymentMethod
) {}