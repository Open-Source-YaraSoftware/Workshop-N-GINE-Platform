package com.yarasoftware.workshopngine.platform.device.interfaces.rest.resources;

import com.yarasoftware.workshopngine.platform.device.domain.model.entities.Code;

import java.util.List;

public record IotDeviceResource(
    Long id,
    List<Code> codeList,
    Long vehicle
) {
}
