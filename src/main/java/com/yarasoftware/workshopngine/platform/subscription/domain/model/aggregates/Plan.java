package com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.BillingCycle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method")
    private String paymentMethod;

    private Float amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private BillingCycle status;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Subscription membership;

    public static Plan create(String paymentMethod, Float amount, Subscription membership) {
        Plan payment = new Plan();
        payment.paymentMethod = paymentMethod;
        payment.amount = amount;
        payment.membership = membership;
        payment.paymentDate = LocalDateTime.now();
        payment.status = BillingCycle.PENDING;
        return payment;
    }

    public void complete() {
        this.status = BillingCycle.COMPLETED;
    }

    public void fail() {
        this.status = BillingCycle.FAILED;
    }

    public void refund() {
        this.status = BillingCycle.REFUNDED;
    }
}