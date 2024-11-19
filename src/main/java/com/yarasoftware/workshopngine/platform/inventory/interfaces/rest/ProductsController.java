package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.DeleteProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.queries.GetAllProductsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.inventory.domain.services.ProductCommandService;
import com.yarasoftware.workshopngine.platform.inventory.domain.services.ProductQueryService;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.CreateProductResource;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.ProductResource;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources.UpdateProductResource;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.transform.UpdateProductCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Product Management Endpoints")
public class ProductsController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductsController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }


    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProductsByWorkshopId(@RequestParam Long workshopId) {
        if (workshopId <= 0) return ResponseEntity.badRequest().build();
        var getAllProductsByWorkshopIdQuery = new GetAllProductsByWorkshopIdQuery(workshopId);
        var products = productQueryService.handle(getAllProductsByWorkshopIdQuery);
        if (products.isEmpty()) return ResponseEntity.badRequest().build();
        var resources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource) {
        if (createProductResource.workshopId() <= 0) return ResponseEntity.badRequest().build();
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(createProductResource);
        var product = productCommandService.handle(createProductCommand);
        if (product.isEmpty()) return ResponseEntity.badRequest().build();
        var resource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(resource);
    }

    @PutMapping("{productId}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductResource updateProductResource) {
        if (productId <= 0) return ResponseEntity.badRequest().build();
        var updateProductCommand = UpdateProductCommandFromResourceAssembler.toCommandFromResource(updateProductResource);
        var product = productCommandService.handle(productId, updateProductCommand);
        if (product.isEmpty()) return ResponseEntity.badRequest().build();
        var resource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        if (productId <= 0) return ResponseEntity.badRequest().build();
        var deleteProductCommand = new DeleteProductCommand(productId);
        productCommandService.handle(deleteProductCommand);
        return ResponseEntity.ok().build();
    }




}
