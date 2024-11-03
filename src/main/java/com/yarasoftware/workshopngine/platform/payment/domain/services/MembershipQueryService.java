package com.yarasoftware.workshopngine.platform.payment.domain.services;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Membership;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetMembershipByIdQuery;

import java.util.Optional;

public interface MembershipQueryService {
    Optional<Membership> getMembershipById(GetMembershipByIdQuery query);
}