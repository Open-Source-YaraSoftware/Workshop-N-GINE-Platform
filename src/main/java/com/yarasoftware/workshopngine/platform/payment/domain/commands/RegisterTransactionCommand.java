package com.yarasoftware.workshopngine.platform.payment.domain.commands;

import com.yarasoftware.workshopngine.platform.payment.domain.model.valueobjects.TransactionType;

public record RegisterTransactionCommand(
        TransactionType transactionType,
        String paymentMethod,
        Float amount,
        Long paymentId
) {}