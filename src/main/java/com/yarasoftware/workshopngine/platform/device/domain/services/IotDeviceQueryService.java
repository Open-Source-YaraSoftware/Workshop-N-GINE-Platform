package com.yarasoftware.workshopngine.platform.device.domain.services;

import com.yarasoftware.workshopngine.platform.device.domain.model.aggregates.IotDevice;
import com.yarasoftware.workshopngine.platform.device.domain.model.queries.GetAllIotDeviceByVehicleIdQuery;

import java.util.List;

public interface IotDeviceQueryService {
    List<IotDevice> handle(GetAllIotDeviceByVehicleIdQuery query);
}
