package com.yarasoftware.workshopngine.platform.payment.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Payment;
import com.yarasoftware.workshopngine.platform.payment.domain.commands.ProcessPaymentCommand;
import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Membership;
import com.yarasoftware.workshopngine.platform.payment.domain.services.PaymentCommandService;
import com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories.MembershipRepository;
import com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;
    private final MembershipRepository membershipRepository;

    @Override
    @Transactional
    public Long processPayment(ProcessPaymentCommand command) {
        Membership membership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        Payment payment = Payment.create(
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
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.fail();
        paymentRepository.save(payment);
    }
}