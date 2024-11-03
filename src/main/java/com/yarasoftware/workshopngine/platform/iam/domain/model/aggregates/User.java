package com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.AccountStatuses;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    @NotNull
    @Positive
    private Long workshopId;

    @NotNull
    private AccountStatuses status;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Role role, Long workshopId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.workshopId = workshopId;
        this.status = AccountStatuses.ACTIVE;
    }

    /**
     * Get the status of the user
     * @return the status of the user
     */
    public String getStatus() {
        return status.name();
    }
}
