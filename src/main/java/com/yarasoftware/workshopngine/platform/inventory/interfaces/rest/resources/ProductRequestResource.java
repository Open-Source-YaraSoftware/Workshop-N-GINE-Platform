package com.yarasoftware.workshopngine.platform.inventory.interfaces.rest.resources;

public record ProductRequestResource(Long id, Long requestedQuantity, Long taskId, Long productId, Long workshopId, String status) {
}
