package com.yarasoftware.workshopngine.platform.subscription.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import com.yarasoftware.workshopngine.platform.subscription.domain.commands.UpdateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Subscription;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.PlanCommandService;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanCommandServiceImpl implements PlanCommandService {
    private final PlanRepository paymentRepository;
    private final SubscriptionRepository membershipRepository;

    @Override
    @Transactional
    public Long processPayment(UpdateSubscriptionCommand command) {
        Subscription membership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        Plan payment = Plan.create(
                command.paymentMethod(),
                command.amount(),
                membership
        );
        payment = paymentRepository.save(payment);

        // Here you would typically integrate with a payment gateway
        // For this example, we'll just mark the payment as completed
        payment.complete();
        return paymentRepository.save(payment).getId();
    }

    @Override
    @Transactional
    public void cancelPayment(Long paymentId) {
        Plan payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        payment.fail();
        paymentRepository.save(payment);
    }
}