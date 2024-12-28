package com.yarasoftware.workshopngine.platform.file.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.file.domain.model.aggregates.File;
import com.yarasoftware.workshopngine.platform.file.domain.model.queries.GetFileByIdQuery;
import com.yarasoftware.workshopngine.platform.file.domain.services.FileQueryService;
import com.yarasoftware.workshopngine.platform.file.infrastructure.persistence.jpa.repositories.FileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileQueryServiceImpl implements FileQueryService {
    private final FileRepository fileRepository;

    public FileQueryServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Optional<File> handle(GetFileByIdQuery query) {
        return fileRepository.findById(query.fileId());
    }
}
