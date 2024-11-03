package com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.payment.domain.model.valueobjects.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method")
    private String paymentMethod;

    private Float amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    public static Payment create(String paymentMethod, Float amount, Membership membership) {
        Payment payment = new Payment();
        payment.paymentMethod = paymentMethod;
        payment.amount = amount;
        payment.membership = membership;
        payment.paymentDate = LocalDateTime.now();
        payment.status = PaymentStatus.PENDING;
        return payment;
    }

    public void complete() {
        this.status = PaymentStatus.COMPLETED;
    }

    public void fail() {
        this.status = PaymentStatus.FAILED;
    }

    public void refund() {
        this.status = PaymentStatus.REFUNDED;
    }
}