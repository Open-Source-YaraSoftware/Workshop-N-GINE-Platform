package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.Product;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product product) {
        return new ProductResource(product.getId(), product.getName(), product.getDescription(), product.getStockQuantity(), product.getLowStockThreshold(), product.getWorkshopId());
    }
}
