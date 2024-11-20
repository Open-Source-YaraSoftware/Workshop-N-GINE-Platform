package com.yarasoftware.workshopngine.platform.subscription.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.commands.UpdateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.SubscriptionCommandService;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.PlanRepository;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository, PlanRepository planRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public Long createSubscription(CreateSubscriptionCommand command) {
        Plan plan = planRepository.findById(command.planId())
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        Subscription subscription = Subscription.create(plan, command.workshopId());
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return savedSubscription.getId();
    }

    @Override
    @Transactional
    public void updateSubscription(UpdateSubscriptionCommand command) {
        Subscription subscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        subscription.setStatus(command.newStatus());
        subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void renewSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        subscription.renew();
        subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void cancelSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        subscription.cancel();
        subscriptionRepository.save(subscription);
    }
}