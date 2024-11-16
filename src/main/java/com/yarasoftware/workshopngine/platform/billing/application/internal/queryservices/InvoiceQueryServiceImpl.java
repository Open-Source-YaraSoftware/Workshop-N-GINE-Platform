package com.yarasoftware.workshopngine.platform.billing.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.billing.domain.model.aggregates.Invoice;
import com.yarasoftware.workshopngine.platform.billing.domain.queries.GetInvoiceByIdQuery;
import com.yarasoftware.workshopngine.platform.billing.domain.queries.GetInvoicesBySubscriptionIdQuery;
import com.yarasoftware.workshopngine.platform.billing.domain.services.InvoiceQueryService;
import com.yarasoftware.workshopngine.platform.billing.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceQueryServiceImpl implements InvoiceQueryService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceQueryServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invoice> getInvoiceById(GetInvoiceByIdQuery query) {
        return invoiceRepository.findById(query.id());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Invoice> getInvoicesBySubscriptionId(GetInvoicesBySubscriptionIdQuery query) {
        return invoiceRepository.findBySubscriptionId(query.subscriptionId());
    }
}