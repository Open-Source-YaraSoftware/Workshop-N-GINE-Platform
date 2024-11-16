package com.yarasoftware.workshopngine.platform.subscription.domain.commands;

public record CreateSubscriptionCommand(
        Long planId,
        Long workshopId
) {}