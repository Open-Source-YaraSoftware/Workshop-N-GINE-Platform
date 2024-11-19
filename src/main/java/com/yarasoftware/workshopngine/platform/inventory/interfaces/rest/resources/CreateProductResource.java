package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources;

public record CreateProductResource(String name, String description, Long stockQuantity, Long lowStockThreshold, Long workshopId) {
}
