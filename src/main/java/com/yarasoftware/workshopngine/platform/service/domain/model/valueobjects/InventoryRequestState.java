package com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects;

/**
 * Represents the state of an inventory request.
 * <p>
 * An inventory request can be in one of the following states:
 * <div><span>{@link #PENDING}</span>, <span>{@link #DELIVERED}</span></div>
 * <p>
 */
public enum InventoryRequestState {
    PENDING,
    DELIVERED
}
