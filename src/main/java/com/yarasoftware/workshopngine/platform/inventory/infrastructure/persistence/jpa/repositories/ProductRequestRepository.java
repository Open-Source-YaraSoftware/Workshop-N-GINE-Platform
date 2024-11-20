package com.yarasoftware.workshopngine.platform.inventory.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long> {

    List<ProductRequest> findAllByWorkshopId(Long workshopId);

    List<ProductRequest> findAllByTaskId(Long productId);
}
