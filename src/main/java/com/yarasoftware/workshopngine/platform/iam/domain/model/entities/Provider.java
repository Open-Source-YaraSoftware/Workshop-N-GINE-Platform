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
}
