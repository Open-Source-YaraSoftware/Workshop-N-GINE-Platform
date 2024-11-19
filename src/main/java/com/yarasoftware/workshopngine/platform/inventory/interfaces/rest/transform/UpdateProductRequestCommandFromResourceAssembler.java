package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.UpdateProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.UpdateProductRequestResource;

public class UpdateProductRequestCommandFromResourceAssembler {
    public static UpdateProductRequestCommand toCommandFromResource(UpdateProductRequestResource resource) {
        return new UpdateProductRequestCommand(resource.requestedQuantity(), resource.productId());
    }
}
