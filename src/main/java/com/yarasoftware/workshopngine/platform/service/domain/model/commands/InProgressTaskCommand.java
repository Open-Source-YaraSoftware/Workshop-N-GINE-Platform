package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

public record InProgressTaskCommand(Long taskId) {
    public InProgressTaskCommand {
        if (taskId == null || taskId <= 0)
            throw new IllegalArgumentException("Task ID must not be null");
    }
}
