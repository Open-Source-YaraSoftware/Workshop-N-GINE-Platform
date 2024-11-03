package com.yarasoftware.workshopngine.platform.payment.domain.services;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Transaction;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetTransactionByIdQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetTransactionsByPaymentIdQuery;

import java.util.List;
import java.util.Optional;

public interface TransactionQueryService {
    Optional<Transaction> getTransactionById(GetTransactionByIdQuery query);
    List<Transaction> getTransactionsByPaymentId(GetTransactionsByPaymentIdQuery query);
}