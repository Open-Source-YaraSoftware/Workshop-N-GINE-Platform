package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources.SubscriptionResource;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class SubscriptionResourceFromEntityAssembler {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    public SubscriptionResource toResource(Subscription entity) {
        return new SubscriptionResource(
                entity.getId(),
                entity.getMembershipType().toString(),
                entity.getStartDate().format(DATE_FORMAT),
                entity.getEndDate().format(DATE_FORMAT),
                entity.getAmount(),
                entity.getWorkshopId()
        );
    }
}