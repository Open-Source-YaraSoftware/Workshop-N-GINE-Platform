package com.yarasoftware.workshopngine.platform.billing.interfaces.rest.resources;

import com.yarasoftware.workshopngine.platform.billing.domain.model.valueobjects.Currency;
import com.yarasoftware.workshopngine.platform.billing.domain.model.valueobjects.InvoiceStatus;

import java.time.LocalDateTime;

public record InvoiceResource(
        Long id,
        Long subscriptionId,
        Integer amount,
        Currency currency,
        InvoiceStatus status,
        LocalDateTime issueDate,
        LocalDateTime dueDate,
        LocalDateTime paymentDate
) {}