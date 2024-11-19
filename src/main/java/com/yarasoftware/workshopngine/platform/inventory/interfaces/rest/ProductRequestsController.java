package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates.ProductRequest;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.AcceptProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.RejectProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductRequestsByTaskIdQuery;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductRequestsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.inventory.domain.services.ProductRequestCommandService;
import com.yarasoftware.workshopngine.platform.inventory.domain.services.ProductRequestQueryService;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.CreateProductRequestResource;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.ProductRequestResource;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.UpdateProductRequestResource;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform.CreateProductRequestCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform.ProductRequestResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform.UpdateProductRequestCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/product-requests", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product Requests", description = "Product Requests Management Endpoints")
public class ProductRequestsController {
    private final ProductRequestCommandService productRequestCommandService;
    private final ProductRequestQueryService productRequestQueryService;

    public ProductRequestsController(ProductRequestCommandService productRequestCommandService, ProductRequestQueryService productRequestQueryService) {
        this.productRequestCommandService = productRequestCommandService;
        this.productRequestQueryService = productRequestQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductRequestResource>> getAllProductRequests(@RequestParam Long workshopId, @RequestParam Long taskId){

        List<ProductRequest> productRequests;
        if (workshopId != 0 && !(workshopId <= 0)){
            var query = new GetAllProductRequestsByWorkshopIdQuery(workshopId);
            productRequests = productRequestQueryService.handle(query);
        } else if (taskId != 0 && !(taskId <= 0)){
            var query = new GetAllProductRequestsByTaskIdQuery(taskId);
            productRequests = productRequestQueryService.handle(query);
        }else {
            return ResponseEntity.badRequest().build();
        }
        var resources = productRequests.stream()
                .map(ProductRequestResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }


    @PostMapping
    public ResponseEntity<ProductRequestResource> createProductRequest(@RequestBody CreateProductRequestResource createProductRequestResource){
        if (createProductRequestResource.workshopId() <= 0 || createProductRequestResource.taskId() <= 0 || createProductRequestResource.productId() <= 0 || createProductRequestResource.requestedQuantity() <= 0){
            return ResponseEntity.badRequest().build();
        }
        var command = CreateProductRequestCommandFromResourceAssembler.toCommandFromResource(createProductRequestResource);
        var productRequest = productRequestCommandService.handle(command);
        if (productRequest.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var resource = ProductRequestResourceFromEntityAssembler.toResourceFromEntity(productRequest.get());
        return ResponseEntity.ok(resource);
    }

    @PutMapping("{productRequestId}")
    public ResponseEntity<ProductRequestResource> updateProductRequest(@PathVariable Long productRequestId, @RequestBody UpdateProductRequestResource updateProductRequestResource){
        if (productRequestId <= 0){
            return ResponseEntity.badRequest().build();
        }
        var command = UpdateProductRequestCommandFromResourceAssembler.toCommandFromResource(updateProductRequestResource);
        var productRequest = productRequestCommandService.handle(productRequestId, command);
        if (productRequest.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var resource = ProductRequestResourceFromEntityAssembler.toResourceFromEntity(productRequest.get());
        return ResponseEntity.ok(resource);
    }

    @PostMapping("{productRequestId}/accept")
    public ResponseEntity<String> acceptProductRequest(@PathVariable Long productRequestId){
        if (productRequestId <= 0) return ResponseEntity.badRequest().build();

        var acceptProductRequestCommand = new AcceptProductRequestCommand(productRequestId);
        var productRequest = productRequestCommandService.handle(acceptProductRequestCommand);
        if (productRequest == 0 || !productRequest.equals(productRequestId)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Product request accepted successfully");
    }

    @PostMapping("{productRequestId}/reject")
    public ResponseEntity<String> rejectProductRequest(@PathVariable Long productRequestId){
        if (productRequestId <= 0) return ResponseEntity.badRequest().build();

        var rejectProductRequestCommand = new RejectProductRequestCommand(productRequestId);
        var productRequest = productRequestCommandService.handle(rejectProductRequestCommand);
        if (productRequest == 0 || !productRequest.equals(productRequestId)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Product request rejected successfully");
    }

}
