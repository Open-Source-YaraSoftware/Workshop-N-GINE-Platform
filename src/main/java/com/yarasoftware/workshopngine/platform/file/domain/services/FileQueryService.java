package com.yarasoftware.workshopngine.platform.file.domain.services;

import com.yarasoftware.workshopngine.platform.file.domain.model.aggregates.File;
import com.yarasoftware.workshopngine.platform.file.domain.model.queries.GetFileByIdQuery;

import java.util.Optional;

public interface FileQueryService {
    Optional<File> handle(GetFileByIdQuery query);
}
