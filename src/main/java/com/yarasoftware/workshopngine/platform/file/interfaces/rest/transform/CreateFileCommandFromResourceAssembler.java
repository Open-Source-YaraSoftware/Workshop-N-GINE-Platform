package com.yarasoftware.workshopngine.platform.file.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.file.domain.model.commands.CreateFileCommand;
import com.yarasoftware.workshopngine.platform.file.interfaces.rest.resources.CreateFileResource;

import java.io.IOException;

public class CreateFileCommandFromResourceAssembler {
    public static CreateFileCommand toCommandFromResource(CreateFileResource resource) throws IOException {
        return new CreateFileCommand(
                resource.file().getOriginalFilename(),
                resource.file().getContentType(),
                resource.file().getSize(),
                resource.file().getBytes()
        );
    }
}
