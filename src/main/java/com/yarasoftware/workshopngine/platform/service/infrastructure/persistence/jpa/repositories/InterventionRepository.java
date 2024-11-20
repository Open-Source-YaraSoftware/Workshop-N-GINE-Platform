package com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Intervention;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.InterventionStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    boolean existsByWorkshopIdAndVehicleIdAndStatusIs(Long workshopId, Long vehicleId, InterventionStatuses status);
    boolean existsByMechanicLeaderIdAndScheduledAtIsBetween(Long mechanicLeaderId, LocalDateTime start, LocalDateTime end);
    boolean existsByWorkshopIdAndScheduledAtIsBetween(Long workshopId, LocalDateTime start, LocalDateTime end);
    List<Intervention> findAllByVehicleId(Long vehicleId);
    List<Intervention> findAllByWorkshopId(Long workshopId);
    List<Intervention> findAllByWorkshopIdAndMechanicLeaderId(Long workshopId, Long mechanicLeaderId);
    List<Intervention> findAllByWorkshopIdAndMechanicLeaderIdIsNot(Long workshopId, Long mechanicLeaderId);
}
