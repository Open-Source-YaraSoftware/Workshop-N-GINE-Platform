package com.yarasoftware.workshopngine.platform.billing.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.billing.domain.model.aggregates.Invoice;
import com.yarasoftware.workshopngine.platform.billing.interfaces.rest.resources.InvoiceResource;
import org.springframework.stereotype.Component;

@Component
public class InvoiceResourceFromEntityAssembler {
    public InvoiceResource toResource(Invoice entity) {
        return new InvoiceResource(
                entity.getId(),
                entity.getSubscriptionId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getStatus(),
                entity.getIssueDate(),
                entity.getDueDate(),
                entity.getPaymentDate()
        );
    }
}