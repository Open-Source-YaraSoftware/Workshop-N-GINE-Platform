package com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Provider;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.AuthProviders;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User entity
 * @summary
 * This class represents the user entity in the system.
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@Entity
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Unique
    private String username;

    @Unique
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @NotNull
    private boolean enabled;

    @Embedded
    private Provider authProvider;

    public User() {
        this.username = Strings.EMPTY;
        this.email = Strings.EMPTY;
        this.password = Strings.EMPTY;
        this.roles = new HashSet<>();
        this.enabled = false;
        this.authProvider = new Provider();
    }

    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, List<Role> roles) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        addRoles(roles);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
    }

    public void activateAccount() {
        this.enabled = true;
    }

    public void deactivateAccount() {
        this.enabled = false;
    }

    public void useGoogleAsExternalAuthProvider() {
        this.authProvider.setProviderName(AuthProviders.GOOGLE);
    }

    public void useGithubAsExternalAuthProvider() {
        this.authProvider.setProviderName(AuthProviders.GITHUB);
    }

    public void updateProviderUserId(String providerUserId) {
        this.authProvider.setProviderUserId(providerUserId);
    }
}
