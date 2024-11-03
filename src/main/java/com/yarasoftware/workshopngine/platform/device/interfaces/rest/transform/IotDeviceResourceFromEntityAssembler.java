package com.yarasoftware.workshopngine.platform.device.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.device.domain.model.aggregates.IotDevice;
import com.yarasoftware.workshopngine.platform.device.interfaces.rest.resources.IotDeviceResource;

public class IotDeviceResourceFromEntityAssembler {
    public static IotDeviceResource toResourceFromEntity(IotDevice entity) {
        return new IotDeviceResource(entity.getId(), entity.getCodeList(), entity.getVehicleId());
    }
}
