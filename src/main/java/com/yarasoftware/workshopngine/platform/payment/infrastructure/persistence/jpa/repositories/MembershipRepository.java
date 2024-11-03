package com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByWorkshopId(Long workshopId);
}