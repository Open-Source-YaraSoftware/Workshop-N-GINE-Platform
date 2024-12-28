package com.yarasoftware.workshopngine.platform.file.interfaces.rest.transform;

import com.yarasoftware.workshopngine.platform.file.domain.model.aggregates.File;
import com.yarasoftware.workshopngine.platform.file.interfaces.rest.resources.FileResource;

public class FileResourceFromEntityAssembler {
    public static FileResource toResourceFromEntity(File entity){
        return new FileResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getSize(),
                entity.getData()
        );
    }
}
