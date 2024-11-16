package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources;

public record PlanResource(
        Long id,
        String paymentMethod,
        Float amount,
        String paymentDate,
        String status,
        Long membershipId
) {}