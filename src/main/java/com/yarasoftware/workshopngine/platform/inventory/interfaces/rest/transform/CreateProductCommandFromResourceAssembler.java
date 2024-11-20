package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.CreateProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(
                resource.name(),
                resource.description(),
                resource.stockQuantity(),
                resource.lowStockThreshold(),
                resource.workshopId()
        );
    }
}
