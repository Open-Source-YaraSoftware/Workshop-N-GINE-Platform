package com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources;

public record PaymentResource(
        Long id,
        String paymentMethod,
        Float amount,
        String paymentDate,
        String status,
        Long membershipId
) {}