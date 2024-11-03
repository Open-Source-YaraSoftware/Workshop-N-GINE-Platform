package com.yarasoftware.workshopngine.platform.payment.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Payment;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetPaymentHistoryQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.services.PaymentQueryService;
import com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getPaymentHistory(GetPaymentHistoryQuery query) {
        return paymentRepository.findByMembershipId(query.membershipId());
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}