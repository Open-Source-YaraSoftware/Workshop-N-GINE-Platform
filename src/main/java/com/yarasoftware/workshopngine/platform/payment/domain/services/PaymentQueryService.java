package com.yarasoftware.workshopngine.platform.payment.domain.services;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Payment;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetPaymentHistoryQuery;

import java.util.List;

public interface PaymentQueryService {
    List<Payment> getPaymentHistory(GetPaymentHistoryQuery query);
    List<Payment> getAllPayments();
}