package com.yarasoftware.workshopngine.platform.payment.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Transaction;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources.TransactionResource;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class TransactionResourceFromEntityAssembler {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    public TransactionResource toResource(Transaction entity) {
        return new TransactionResource(
                entity.getId(),
                entity.getTransactionType().toString(),
                entity.getPaymentMethod(),
                entity.getAmount(),
                entity.getDate().format(DATE_FORMAT),
                entity.getStatus(),
                entity.getPayment().getId()
        );
    }
}