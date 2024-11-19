package com.yarasoftware.workshopngine.platform.subscription.domain.commands;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.SubscriptionStatus;

public record UpdateSubscriptionCommand(
        Long subscriptionId,
        SubscriptionStatus newStatus
) {}