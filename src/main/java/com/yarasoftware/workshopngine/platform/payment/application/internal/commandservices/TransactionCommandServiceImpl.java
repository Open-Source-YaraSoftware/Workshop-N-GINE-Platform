package com.yarasoftware.workshopngine.platform.payment.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.payment.domain.commands.RegisterTransactionCommand;
import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Payment;
import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Transaction;
import com.yarasoftware.workshopngine.platform.payment.domain.services.TransactionCommandService;
import com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories.PaymentRepository;
import com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionCommandServiceImpl implements TransactionCommandService {
    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public Long registerTransaction(RegisterTransactionCommand command) {
        Payment payment = paymentRepository.findById(command.paymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Transaction transaction = Transaction.register(
                command.transactionType(),
                command.paymentMethod(),
                command.amount(),
                payment
        );
        return transactionRepository.save(transaction).getId();
    }

    @Override
    @Transactional
    public void approveTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.approveTransaction();
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void declineTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.declineTransaction();
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void updateTransactionStatus(Long transactionId, String newStatus) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.updateStatus(newStatus);
        transactionRepository.save(transaction);
    }
}