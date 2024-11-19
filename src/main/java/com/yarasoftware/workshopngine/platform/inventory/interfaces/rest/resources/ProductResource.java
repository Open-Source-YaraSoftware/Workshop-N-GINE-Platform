package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources;

public record ProductResource(Long id, String name, String description, Long stockQuantity, Long lowStockThreshold, Long workshopId) {
}
