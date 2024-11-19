package com.yarasoftware.workshopngine.platform.inventory.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.Product;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.CreateProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.DeleteProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.UpdateProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.services.ProductCommandService;
import com.yarasoftware.workshopngine.platform.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {

        var product = new Product(command);
        productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public Optional<Product> handle(Long productId, UpdateProductCommand command) {

        var product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new RuntimeException("Product with the id " + productId + " does not exist");

        product.get().Update(command);
        productRepository.save(product.get());

        return product;
    }

    @Override
    public void handle(DeleteProductCommand command) {

        if(!productRepository.existsById(command.productId()))
            throw new RuntimeException("Product with the id " + command.productId() + " does not exist");

        productRepository.deleteById(command.productId());
    }
}
