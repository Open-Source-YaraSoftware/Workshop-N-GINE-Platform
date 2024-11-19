package com.yarasoftware.workshopngine.platform.inventory.domain.model.commands;

public record CreateProductCommand(String name, String description, Long stockQuantity, Long lowStockThreshold, Long workshopId) {
}
