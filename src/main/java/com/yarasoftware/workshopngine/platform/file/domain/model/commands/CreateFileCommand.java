package com.yarasoftware.workshopngine.platform.file.domain.model.commands;

public record CreateFileCommand(String name, String type, Long size, byte[] data) {
    public CreateFileCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (size == null || size < 0) {
            throw new IllegalArgumentException("Size cannot be null or negative");
        }
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }
    }
}
