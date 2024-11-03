package com.yarasoftware.workshopngine.platform.payment.interfaces.rest.transform;

import  com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Membership;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources.MembershipResource;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class MembershipResourceFromEntityAssembler {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    public MembershipResource toResource(Membership entity) {
        return new MembershipResource(
                entity.getId(),
                entity.getMembershipType().toString(),
                entity.getStartDate().format(DATE_FORMAT),
                entity.getEndDate().format(DATE_FORMAT),
                entity.getAmount(),
                entity.getWorkshopId()
        );
    }
}