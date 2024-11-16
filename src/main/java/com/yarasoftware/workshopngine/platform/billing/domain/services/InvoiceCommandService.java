package com.yarasoftware.workshopngine.platform.billing.domain.services;

import com.yarasoftware.workshopngine.platform.billing.domain.commands.CreateInvoiceCommand;
import com.yarasoftware.workshopngine.platform.billing.domain.commands.UpdateInvoiceStatusCommand;

public interface InvoiceCommandService {
    Long createInvoice(CreateInvoiceCommand command);
    void updateInvoiceStatus(UpdateInvoiceStatusCommand command);
}