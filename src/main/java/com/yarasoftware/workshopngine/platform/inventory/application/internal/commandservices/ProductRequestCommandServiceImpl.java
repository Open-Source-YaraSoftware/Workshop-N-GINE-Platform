package com.yarasoftware.workshopngine.platform.inventory.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.ProductRequest;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.AcceptProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.CreateProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.RejectProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.UpdateProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.services.ProductRequestCommandService;
import com.yarasoftware.workshopngine.platform.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.yarasoftware.workshopngine.platform.inventory.infrastructure.persistence.jpa.repositories.ProductRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductRequestCommandServiceImpl implements ProductRequestCommandService {
    private final ProductRequestRepository productRequestRepository;
    private final ProductRepository productRepository;

    public ProductRequestCommandServiceImpl(ProductRequestRepository productRequestRepository, ProductRepository productRepository) {
        this.productRequestRepository = productRequestRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<ProductRequest> handle(CreateProductRequestCommand command) {

        var product = productRepository.findById(command.productId());
        if(product.isEmpty())
            throw new RuntimeException("Product with the id " + command.productId() + " does not exist");
        if(!product.get().IsAvailableRequest(command.requestedQuantity()))
            throw new RuntimeException("Product with the id " + command.productId() + " does not have enough stock");

        var productRequest = new ProductRequest(command);
        productRequestRepository.save(productRequest);
        return Optional.of(productRequest);
    }

    @Override
    public Optional<ProductRequest> handle(Long productRequestId, UpdateProductRequestCommand command) {

        var productRequest = productRequestRepository.findById(productRequestId);
        if(productRequest.isEmpty())
            throw new RuntimeException("Product request with the id " + productRequestId + " does not exist");
        var product = productRepository.findById(command.productId());
        if(product.isEmpty())
            throw new RuntimeException("Product with the id " + command.productId() + " does not exist");
        if(!product.get().IsAvailableRequest(command.requestedQuantity()))
            throw new RuntimeException("Product with the id " + command.productId() + " does not have enough stock");

        productRequest.get().Update(command);
        productRequestRepository.save(productRequest.get());
        return productRequest;
    }

    @Override
    public Long handle(AcceptProductRequestCommand command) {

        var productRequest = productRequestRepository.findById(command.productRequestId());
        if(productRequest.isEmpty())
            throw new RuntimeException("Product request with the id " + command.productRequestId() + " does not exist");

        var product = productRepository.findById(productRequest.get().getProductId());
        if(product.isEmpty())
            throw new RuntimeException("Product with the id " + productRequest.get().getProductId() + " does not exist");

        if (!product.get().IsAvailableRequest(productRequest.get().getRequestedQuantity()))
            throw new RuntimeException("Product with the id " + productRequest.get().getProductId() + " does not have enough stock");

        product.get().Request(productRequest.get().getRequestedQuantity());
        productRequest.get().Accept();

        productRepository.save(product.get());
        productRequestRepository.save(productRequest.get());

        return productRequest.get().getId();

    }

    @Override
    public Long handle(RejectProductRequestCommand command) {
        var productRequest = productRequestRepository.findById(command.productRequestId());
        if(productRequest.isEmpty())
            throw new RuntimeException("Product request with the id " + command.productRequestId() + " does not exist");
        productRequest.get().Reject();
        productRequestRepository.save(productRequest.get());
        return productRequest.get().getId();
    }
}
