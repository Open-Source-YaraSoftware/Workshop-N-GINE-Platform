package com.yarasoftware.workshopngine.platform.subscription.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetAllSubscriptionsQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetSubscriptionByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.SubscriptionQueryService;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Subscription> getSubscriptionById(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.id());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> getAllSubscriptions(GetAllSubscriptionsQuery query) {
        return subscriptionRepository.findAll();
    }
}