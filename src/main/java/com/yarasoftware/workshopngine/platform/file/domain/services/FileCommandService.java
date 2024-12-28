package com.yarasoftware.workshopngine.platform.file.domain.services;

import com.yarasoftware.workshopngine.platform.file.domain.model.aggregates.File;
import com.yarasoftware.workshopngine.platform.file.domain.model.commands.CreateFileCommand;

import java.util.Optional;

public interface FileCommandService {
    Optional<File> handle(CreateFileCommand command);
}
