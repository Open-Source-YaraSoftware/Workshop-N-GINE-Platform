package com.yarasoftware.workshopngine.platform.inventory.domain.services;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.ProductRequest;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.AcceptProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.CreateProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.RejectProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.UpdateProductRequestCommand;

import java.util.Optional;

public interface ProductRequestCommandService {

    Optional<ProductRequest> handle(CreateProductRequestCommand command);

    Optional<ProductRequest> handle(Long productRequestId, UpdateProductRequestCommand command);

    Long handle(AcceptProductRequestCommand command);

    Long handle(RejectProductRequestCommand command);

}
