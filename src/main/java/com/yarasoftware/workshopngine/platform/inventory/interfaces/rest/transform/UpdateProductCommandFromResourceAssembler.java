package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.UpdateProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(UpdateProductResource resource) {
        return new UpdateProductCommand(
                resource.name(),
                resource.description(),
                resource.stockQuantity(),
                resource.lowStockThreshold()
        );
    }
}
