package com.yarasoftware.workshopngine.platform.subscription.domain.services;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.commands.UpdateSubscriptionCommand;

public interface SubscriptionCommandService {
    Long createSubscription(CreateSubscriptionCommand command);
    void updateSubscription(UpdateSubscriptionCommand command);
    void renewSubscription(Long subscriptionId);
    void cancelSubscription(Long subscriptionId);
}