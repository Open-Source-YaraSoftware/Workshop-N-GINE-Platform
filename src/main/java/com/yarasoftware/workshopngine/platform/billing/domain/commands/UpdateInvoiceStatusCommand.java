package com.yarasoftware.workshopngine.platform.billing.domain.commands;

import com.yarasoftware.workshopngine.platform.billing.domain.model.valueobjects.InvoiceStatus;

public record UpdateInvoiceStatusCommand(
        Long invoiceId,
        InvoiceStatus newStatus
) {}