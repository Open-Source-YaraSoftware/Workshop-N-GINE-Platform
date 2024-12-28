package com.yarasoftware.workshopngine.platform.file.domain.model.queries;

public record GetFileByIdQuery(Long fileId) {
    public GetFileByIdQuery {
        if (fileId == null || fileId <= 0) {
            throw new IllegalArgumentException("File id cannot be null or less than or equal to zero");
        }
    }
}
