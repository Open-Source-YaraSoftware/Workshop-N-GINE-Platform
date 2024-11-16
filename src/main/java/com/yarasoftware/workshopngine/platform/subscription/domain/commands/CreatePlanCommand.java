package com.yarasoftware.workshopngine.platform.subscription.domain.commands;

public record CreatePlanCommand(
        TransactionType transactionType,
        String paymentMethod,
        Float amount,
        Long paymentId
) {}