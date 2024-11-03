package com.yarasoftware.workshopngine.platform.payment.domain.services;

import com.yarasoftware.workshopngine.platform.payment.domain.commands.ProcessPaymentCommand;

public interface PaymentCommandService {
    Long processPayment(ProcessPaymentCommand command);
    void cancelPayment(Long paymentId);
}