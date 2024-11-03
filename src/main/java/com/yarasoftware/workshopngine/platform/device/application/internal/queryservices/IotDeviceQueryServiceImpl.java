package com.yarasoftware.workshopngine.platform.device.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.device.domain.model.aggregates.IotDevice;
import com.yarasoftware.workshopngine.platform.device.domain.model.queries.GetAllIotDeviceByVehicleIdQuery;
import com.yarasoftware.workshopngine.platform.device.domain.services.IotDeviceQueryService;
import com.yarasoftware.workshopngine.platform.device.infrastructure.persistence.jpa.repositories.IotDeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IotDeviceQueryServiceImpl implements IotDeviceQueryService {
    private final IotDeviceRepository iotDeviceRepository;

    public IotDeviceQueryServiceImpl(IotDeviceRepository iotDeviceRepository) {
        this.iotDeviceRepository = iotDeviceRepository;
    }

    @Override
    public List<IotDevice> handle(GetAllIotDeviceByVehicleIdQuery query) {
        return iotDeviceRepository.findAllByVehicleId(query.vehicleId());
    }
}
