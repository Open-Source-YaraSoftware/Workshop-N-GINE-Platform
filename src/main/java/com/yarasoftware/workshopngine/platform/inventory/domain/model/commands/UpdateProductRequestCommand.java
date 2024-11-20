package com.yarasoftware.workshopngine.platform.inventory.domain.model.commands;

public record UpdateProductRequestCommand(Long requestedQuantity, Long productId) {
}
