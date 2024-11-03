package com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources;

public record TransactionResource(
        Long id,
        String transactionType,
        String paymentMethod,
        Float amount,
        String date,
        String status,
        Long paymentId
) {}