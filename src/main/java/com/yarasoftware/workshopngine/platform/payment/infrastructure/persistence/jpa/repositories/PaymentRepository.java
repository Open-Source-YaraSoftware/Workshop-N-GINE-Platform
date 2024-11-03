package com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByMembershipId(Long membershipId);
}