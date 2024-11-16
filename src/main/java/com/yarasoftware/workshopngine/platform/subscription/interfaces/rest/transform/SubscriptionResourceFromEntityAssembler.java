package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources.SubscriptionResource;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionResourceFromEntityAssembler {
    public SubscriptionResource toResource(Subscription entity) {
        return new SubscriptionResource(
                entity.getId(),
                entity.getPlan().getId(),
                entity.getStatus(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getWorkshopId()
        );
    }
}