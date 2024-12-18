package com.yarasoftware.workshopngine.platform.iam.domain.model.entities;

import com.yarasoftware.workshopngine.platform.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Role entity
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 20)
    private Roles name;

    public Role(Roles name) { this.name = name; }

    public String getStringName() {
        return name.name();
    }

    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_WORKSHOP_OWNER);
    }

    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }
        return roles;
    }
}
