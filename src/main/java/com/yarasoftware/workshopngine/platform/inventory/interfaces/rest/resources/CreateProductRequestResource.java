package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources;

public record CreateProductRequestResource(Long requestedQuantity, Long taskId, Long productId, Long workshopId) {
}
