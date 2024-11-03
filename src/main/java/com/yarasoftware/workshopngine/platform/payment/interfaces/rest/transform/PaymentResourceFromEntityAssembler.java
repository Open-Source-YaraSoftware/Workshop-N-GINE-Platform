package com.yarasoftware.workshopngine.platform.payment.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Payment;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources.PaymentResource;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class PaymentResourceFromEntityAssembler {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    public PaymentResource toResource(Payment entity) {
        return new PaymentResource(
                entity.getId(),
                entity.getPaymentMethod(),
                entity.getAmount(),
                entity.getPaymentDate().format(DATE_FORMAT),
                entity.getStatus().toString(),
                entity.getMembership().getId()
        );
    }
}