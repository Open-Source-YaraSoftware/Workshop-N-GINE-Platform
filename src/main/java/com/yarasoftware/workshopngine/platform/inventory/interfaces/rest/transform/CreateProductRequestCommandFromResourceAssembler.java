package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.CreateProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.CreateProductRequestResource;

public class CreateProductRequestCommandFromResourceAssembler {
    public static CreateProductRequestCommand toCommandFromResource(CreateProductRequestResource resource) {
        return new CreateProductRequestCommand(
            resource.requestedQuantity(),
            resource.taskId(),
            resource.productId(),
            resource.workshopId()
        );
    }
}
