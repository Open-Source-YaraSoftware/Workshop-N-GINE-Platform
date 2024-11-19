package com.yarasoftware.workshopngine.platform.inventory.domain.services;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.Product;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.CreateProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.DeleteProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {

    Optional<Product> handle(CreateProductCommand command);

    Optional<Product> handle(Long productId, UpdateProductCommand command);

    void handle(DeleteProductCommand command);

}
