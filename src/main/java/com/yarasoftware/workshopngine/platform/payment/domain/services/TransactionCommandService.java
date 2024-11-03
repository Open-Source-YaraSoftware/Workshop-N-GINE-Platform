package com.yarasoftware.workshopngine.platform.payment.domain.services;

import com.yarasoftware.workshopngine.platform.payment.domain.commands.RegisterTransactionCommand;

public interface TransactionCommandService {
    Long registerTransaction(RegisterTransactionCommand command);
    void approveTransaction(Long transactionId);
    void declineTransaction(Long transactionId);
    void updateTransactionStatus(Long transactionId, String newStatus);
}