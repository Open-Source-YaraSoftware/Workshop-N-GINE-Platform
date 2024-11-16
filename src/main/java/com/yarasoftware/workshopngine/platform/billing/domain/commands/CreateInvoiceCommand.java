package com.yarasoftware.workshopngine.platform.billing.domain.commands;

import com.yarasoftware.workshopngine.platform.billing.domain.model.valueobjects.Currency;

public record CreateInvoiceCommand(
        Long subscriptionId,
        Integer amount,
        Currency currency
) {}