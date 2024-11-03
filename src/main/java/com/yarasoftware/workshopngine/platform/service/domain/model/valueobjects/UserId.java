package com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long userId) {
    public UserId() {
        this(0L);
    }

    public UserId {
        if (userId == null || userId < 0) {
            throw new IllegalArgumentException("User id cannot be null or negative");
        }
    }
}
