package com.yarasoftware.workshopngine.platform.subscription.domain.services;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.UpdateSubscriptionCommand;

public interface PlanCommandService {
    Long processPayment(UpdateSubscriptionCommand command);
    void cancelPayment(Long paymentId);
}