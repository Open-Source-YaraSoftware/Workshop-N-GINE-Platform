package com.yarasoftware.workshopngine.platform.inventory.domain.model.commands;

public record CreateProductRequestCommand(Long requestedQuantity, Long taskId, Long productId, Long workshopId) {
}
