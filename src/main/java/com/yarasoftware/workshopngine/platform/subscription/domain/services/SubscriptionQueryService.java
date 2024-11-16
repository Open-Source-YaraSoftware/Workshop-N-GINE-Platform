package com.yarasoftware.workshopngine.platform.subscription.domain.services;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetSubscriptionByIdQuery;

import java.util.Optional;

public interface SubscriptionQueryService {
    Optional<Subscription> getMembershipById(GetSubscriptionByIdQuery query);
}