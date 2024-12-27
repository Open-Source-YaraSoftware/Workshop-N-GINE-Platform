package com.yarasoftware.workshopngine.platform.iam.domain.model.entities;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.AuthProviders;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

@Embeddable
@Setter
@Getter
public class Provider {
    @Enumerated(EnumType.STRING)
    private AuthProviders providerName;

    @Unique
    private String providerUserId;

    public Provider() {
        this.providerName = AuthProviders.LOCAL;
        this.providerUserId = "";
    }

    public Provider(String providerUserId, String providerName) {
        this.providerUserId = providerUserId;
        this.providerName = verifyProviderName(providerName);
    }

    private AuthProviders verifyProviderName(String providerName) {
        try {
            return AuthProviders.valueOf(providerName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Provider not supported: " + providerName);
        }
    }
}
