package com.yarasoftware.workshopngine.platform.payment.domain.commands;

public record ProcessPaymentCommand(
        Long membershipId,
        String paymentMethod,
        Float amount
) {}