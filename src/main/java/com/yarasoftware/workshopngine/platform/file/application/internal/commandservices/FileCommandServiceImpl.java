package com.yarasoftware.workshopngine.platform.file.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.file.domain.model.aggregates.File;
import com.yarasoftware.workshopngine.platform.file.domain.model.commands.CreateFileCommand;
import com.yarasoftware.workshopngine.platform.file.domain.services.FileCommandService;
import com.yarasoftware.workshopngine.platform.file.infrastructure.persistence.jpa.repositories.FileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileCommandServiceImpl implements FileCommandService {
    private final FileRepository fileRepository;
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 16;

    public FileCommandServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Optional<File> handle(CreateFileCommand command) {
        if (command.size() > MAX_FILE_SIZE) throw new IllegalArgumentException("File with size %s exceeds the maximum allowed size of %s".formatted(command.size(), MAX_FILE_SIZE));
        var file = new File(command);
        try {
            fileRepository.save(file);
            return Optional.of(file);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to save file");
        }
    }
}
