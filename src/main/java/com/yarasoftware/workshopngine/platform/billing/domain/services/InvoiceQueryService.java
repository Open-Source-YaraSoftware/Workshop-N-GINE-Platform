package com.yarasoftware.workshopngine.platform.billing.domain.services;

import com.yarasoftware.workshopngine.platform.billing.domain.model.aggregates.Invoice;
import com.yarasoftware.workshopngine.platform.billing.domain.queries.GetInvoiceByIdQuery;
import com.yarasoftware.workshopngine.platform.billing.domain.queries.GetInvoicesBySubscriptionIdQuery;

import java.util.List;
import java.util.Optional;

public interface InvoiceQueryService {
    Optional<Invoice> getInvoiceById(GetInvoiceByIdQuery query);
    List<Invoice> getInvoicesBySubscriptionId(GetInvoicesBySubscriptionIdQuery query);
}