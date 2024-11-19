package com.yarasoftware.workshopngine.platform.inventory.domain.model.valueobjects;

import lombok.Getter;

@Getter
public enum ProductRequestStatus {
    PENDING(1),
    ACCEPTED(2),
    REJECTED(3);

    private final int value;

    ProductRequestStatus(int value) {
        this.value = value;
    }

}