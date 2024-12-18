package com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.AccountStatuses;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
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

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @NotNull
    @Positive
    private Long workshopId;

    @NotNull
    private AccountStatuses status;

    public User() {
        this.roles = new HashSet<>();
    }

    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, List<Role> roles, Long workshopId) {
        this();
        this.username = username;
        this.password = password;
        this.workshopId = workshopId;
        addRoles(roles);
        this.status = AccountStatuses.ACTIVE;
    }

    /**
     * Get the status of the user
     * @return the status of the user
     */
    public String getStatus() {
        return status.name();
    }

    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public void addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
    }
}
