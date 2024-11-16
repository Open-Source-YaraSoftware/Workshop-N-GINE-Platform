package com.yarasoftware.workshopngine.platform.subscription.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetSubscriptionByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.SubscriptionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {
    private final SubscriptionRepository membershipRepository;

    @Override
    public Optional<Subscription> getMembershipById(GetSubscriptionByIdQuery query) {
        return membershipRepository.findById(query.id());
    }
}