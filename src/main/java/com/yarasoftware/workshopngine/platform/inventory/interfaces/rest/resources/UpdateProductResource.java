package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources;

public record UpdateProductResource(String name, String description, Long stockQuantity, Long lowStockThreshold) {
}
