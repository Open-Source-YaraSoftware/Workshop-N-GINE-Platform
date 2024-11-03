package com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.payment.domain.model.valueobjects.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "payment_method")
    private String paymentMethod;

    private Float amount;

    private LocalDateTime date;

    private String status;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public static Transaction register(TransactionType type, String paymentMethod, Float amount, Payment payment) {
        Transaction transaction = new Transaction();
        transaction.transactionType = type;
        transaction.paymentMethod = paymentMethod;
        transaction.amount = amount;
        transaction.payment = payment;
        transaction.date = LocalDateTime.now();
        transaction.status = "PENDING";
        return transaction;
    }

    public void approveTransaction() {
        this.status = "APPROVED";
    }

    public void declineTransaction() {
        this.status = "DECLINED";
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}