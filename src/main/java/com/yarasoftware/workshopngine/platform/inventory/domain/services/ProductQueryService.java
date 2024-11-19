package com.yarasoftware.workshopngine.platform.inventory.domain.services;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.Product;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductsByWorkshopIdQuery;

import java.util.List;

public interface ProductQueryService {

    List<Product> handle(GetAllProductsByWorkshopIdQuery query);
}
