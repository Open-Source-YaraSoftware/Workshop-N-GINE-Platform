package com.yarasoftware.workshopngine.platform.inventory.domain.services;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.ProductRequest;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductRequestsByTaskIdQuery;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductRequestsByWorkshopIdQuery;

import java.util.List;

public interface ProductRequestQueryService {

    List<ProductRequest> handle(GetAllProductRequestsByTaskIdQuery query);

    List<ProductRequest> handle(GetAllProductRequestsByWorkshopIdQuery query);

}
