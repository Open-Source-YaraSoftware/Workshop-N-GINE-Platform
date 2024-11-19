package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources.PlanResource;
import org.springframework.stereotype.Component;

@Component
public class PlanResourceFromEntityAssembler {
    public PlanResource toResource(Plan entity) {
        return new PlanResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getDurationInMonths(),
                entity.getBillingCycle(),
                entity.getLimitations()
        );
    }
}