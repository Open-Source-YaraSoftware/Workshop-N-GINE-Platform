package com.yarasoftware.workshopngine.platform.iam.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.iam.domain.model.aggregates.User;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Provider;
import com.yarasoftware.workshopngine.platform.iam.domain.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    List<User> findAllByIdAndRolesContains(Long workshopId, Role role);
    Optional<User> findByAuthProvider(Provider provider);
    boolean existsByAuthProvider(Provider provider);
}
