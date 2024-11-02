package com.yarasoftware.workshopngine.platform.service.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Vehicle;
import com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(entity.getId(), entity.getLicensePlate(), entity.getBrand(), entity.getModel(), entity.getRegistrationDate(), entity.getClientId(), entity.getIotDeviceId());
    }
}
