package com.yarasoftware.workshopngine.platform.file.infrastructure.persistence.jpa.repositories;

import com.yarasoftware.workshopngine.platform.file.domain.model.aggregates.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
