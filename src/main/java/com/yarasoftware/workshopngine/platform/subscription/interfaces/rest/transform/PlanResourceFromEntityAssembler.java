package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources.PlanResource;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class PlanResourceFromEntityAssembler {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    public PlanResource toResource(Plan entity) {
        return new PlanResource(
                entity.getId(),
                entity.getPaymentMethod(),
                entity.getAmount(),
                entity.getPaymentDate().format(DATE_FORMAT),
                entity.getStatus().toString(),
                entity.getMembership().getId()
        );
    }
}