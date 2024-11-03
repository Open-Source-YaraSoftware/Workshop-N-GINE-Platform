package com.yarasoftware.workshopngine.platform.payment.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Transaction;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetTransactionByIdQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetTransactionsByPaymentIdQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.services.TransactionQueryService;
import com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionQueryServiceImpl implements TransactionQueryService {
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> getTransactionById(GetTransactionByIdQuery query) {
        return transactionRepository.findById(query.id());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByPaymentId(GetTransactionsByPaymentIdQuery query) {
        return transactionRepository.findByPaymentId(query.paymentId());
    }
}