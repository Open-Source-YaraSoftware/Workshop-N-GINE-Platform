package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.ProductRequest;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.ProductRequestResource;

public class ProductRequestResourceFromEntityAssembler {
    public static ProductRequestResource toResourceFromEntity(ProductRequest entity) {
        return new ProductRequestResource(entity.getId(), entity.getRequestedQuantity(), entity.getTaskId(), entity.getProductId(), entity.getWorkshopId(), entity.StatusToString());
    }
}
