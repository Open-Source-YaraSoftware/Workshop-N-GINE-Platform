package com.yarasoftware.workshopngine.platform.inventory.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.ProductRequest;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductRequestsByTaskIdQuery;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductRequestsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.inventory.domain.services.ProductRequestQueryService;
import com.yarasoftware.workshopngine.platform.inventory.infrastructure.persistence.jpa.repositories.ProductRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRequestQueryServiceImpl implements ProductRequestQueryService {
    private final ProductRequestRepository productRequestRepository;

    public ProductRequestQueryServiceImpl(ProductRequestRepository productRequestRepository) {
        this.productRequestRepository = productRequestRepository;
    }

    @Override
    public List<ProductRequest> handle(GetAllProductRequestsByTaskIdQuery query) {
        return productRequestRepository.findAllByTaskId(query.taskId());
    }

    @Override
    public List<ProductRequest> handle(GetAllProductRequestsByWorkshopIdQuery query) {
        return productRequestRepository.findAllByWorkshopId(query.workshopId());
    }
}
