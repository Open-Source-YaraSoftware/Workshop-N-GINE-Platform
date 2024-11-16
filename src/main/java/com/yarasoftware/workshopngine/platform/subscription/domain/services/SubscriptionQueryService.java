package com.yarasoftware.workshopngine.platform.subscription.domain.services;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetSubscriptionByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetAllSubscriptionsQuery;

import java.util.List;
import java.util.Optional;

public interface SubscriptionQueryService {
    Optional<Subscription> getSubscriptionById(GetSubscriptionByIdQuery query);
    List<Subscription> getAllSubscriptions(GetAllSubscriptionsQuery query);
}