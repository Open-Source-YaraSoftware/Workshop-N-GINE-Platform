package com.yarasoftware.workshopngine.platform.inventory.domain.model.commands;

public record UpdateProductCommand(String name, String description, Long stockQuantity, Long lowStockThreshold) {
}
