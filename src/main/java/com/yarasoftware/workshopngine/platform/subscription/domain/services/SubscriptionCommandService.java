package com.yarasoftware.workshopngine.platform.subscription.domain.services;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreateSubscriptionCommand;

public interface SubscriptionCommandService {
    Long createMembership(CreateSubscriptionCommand command);
    void renewMembership(Long membershipId);
}