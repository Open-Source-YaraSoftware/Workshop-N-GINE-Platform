package com.yarasoftware.workshopngine.platform.billing.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.billing.domain.model.aggregates.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findBySubscriptionId(Long subscriptionId);
}