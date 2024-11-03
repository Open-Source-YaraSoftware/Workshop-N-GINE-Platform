package com.yarasoftware.workshopngine.platform.device.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.device.domain.model.aggregates.IotDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IotDeviceRepository extends JpaRepository<IotDevice, Long> {
    List<IotDevice> findAllByVehicleId(Long vehicleId);
}
