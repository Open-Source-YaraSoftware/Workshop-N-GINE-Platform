package com.yarasoftware.workshopngine.platform.billing.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.billing.domain.model.valueobjects.Currency;
import com.yarasoftware.workshopngine.platform.billing.domain.model.valueobjects.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Getter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscription_id")
    private Long subscriptionId;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    public static Invoice create(Long subscriptionId, Integer amount, Currency currency) {
        Invoice invoice = new Invoice();
        invoice.subscriptionId = subscriptionId;
        invoice.amount = amount;
        invoice.currency = currency;
        invoice.status = InvoiceStatus.UNPAID;
        invoice.issueDate = LocalDateTime.now();
        invoice.dueDate = LocalDateTime.now().plusDays(30);
        return invoice;
    }

    public void markAsPaid() {
        this.status = InvoiceStatus.PAID;
        this.paymentDate = LocalDateTime.now();
    }
}