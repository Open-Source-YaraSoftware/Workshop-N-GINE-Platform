package com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProfileId(Long profileId) {
    public ProfileId() {
        this(0L);
    }

    public ProfileId {
        if (profileId == null || profileId < 0) {
            throw new IllegalArgumentException("ProfileId cannot be null or negative");
        }
    }
}
