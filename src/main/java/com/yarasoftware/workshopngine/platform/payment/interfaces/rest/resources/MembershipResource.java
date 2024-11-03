package com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources;

public record MembershipResource(
        Long id,
        String membershipType,
        String startDate,
        String endDate,
        Float amount,
        Long workshopId
) {}