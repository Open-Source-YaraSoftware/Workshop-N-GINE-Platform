package com.yarasoftware.workshopngine.platform.inventory.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.Product;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.inventory.domain.services.ProductQueryService;
import com.yarasoftware.workshopngine.platform.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> handle(GetAllProductsByWorkshopIdQuery query) {
        return productRepository.findAllByWorkshopId(query.workshopId());
    }
}
