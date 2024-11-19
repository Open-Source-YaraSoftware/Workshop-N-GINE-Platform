package com.yarasoftware.workshopngine.platform.inventory.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByWorkshopId(Long workshopId);

}
