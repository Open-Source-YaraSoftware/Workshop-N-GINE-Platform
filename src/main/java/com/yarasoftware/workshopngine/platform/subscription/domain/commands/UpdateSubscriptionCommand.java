package com.yarasoftware.workshopngine.platform.subscription.domain.commands;

public record UpdateSubscriptionCommand(
        Long membershipId,
        String paymentMethod,
        Float amount
) {}