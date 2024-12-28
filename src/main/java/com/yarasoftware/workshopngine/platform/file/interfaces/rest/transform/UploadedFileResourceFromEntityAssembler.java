package com.yarasoftware.workshopngine.platform.file.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.file.domain.model.aggregates.File;
import com.yarasoftware.workshopngine.platform.file.interfaces.rest.resources.UploadedFileResource;

public class UploadedFileResourceFromEntityAssembler {
    public static UploadedFileResource toResourceFromEntity(File entity) {
        return new UploadedFileResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getSize()
        );
    }
}
