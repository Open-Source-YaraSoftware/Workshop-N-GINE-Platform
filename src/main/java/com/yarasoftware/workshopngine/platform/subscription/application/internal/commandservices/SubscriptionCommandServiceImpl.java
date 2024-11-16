package com.yarasoftware.workshopngine.platform.subscription.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.SubscriptionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {
    private final SubscriptionRepository membershipRepository;

    @Override
    @Transactional
    public Long createMembership(CreateSubscriptionCommand command) {
        Subscription membership = Subscription.create(
                command.type(),
                command.amount(),
                command.workshopId()
        );
        return membershipRepository.save(membership).getId();
    }

    @Override
    @Transactional
    public void renewMembership(Long membershipId) {
        Subscription membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        membership.renew();
        membershipRepository.save(membership);
    }
}