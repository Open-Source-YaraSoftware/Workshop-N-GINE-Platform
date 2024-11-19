package com.yarasoftware.workshopngine.platform.billing.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.billing.domain.commands.CreateInvoiceCommand;
import com.yarasoftware.workshopngine.platform.billing.domain.commands.UpdateInvoiceStatusCommand;
import com.yarasoftware.workshopngine.platform.billing.domain.model.aggregates.Invoice;
import com.yarasoftware.workshopngine.platform.billing.domain.model.valueobjects.InvoiceStatus;
import com.yarasoftware.workshopngine.platform.billing.domain.services.InvoiceCommandService;
import com.yarasoftware.workshopngine.platform.billing.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceCommandServiceImpl implements InvoiceCommandService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceCommandServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    @Transactional
    public Long createInvoice(CreateInvoiceCommand command) {
        Invoice invoice = Invoice.create(
                command.subscriptionId(),
                command.amount(),
                command.currency()
        );
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return savedInvoice.getId();
    }

    @Override
    @Transactional
    public void updateInvoiceStatus(UpdateInvoiceStatusCommand command) {
        Invoice invoice = invoiceRepository.findById(command.invoiceId())
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));

        if (command.newStatus() == InvoiceStatus.PAID) {
            invoice.markAsPaid();
        }

        invoiceRepository.save(invoice);
    }
}