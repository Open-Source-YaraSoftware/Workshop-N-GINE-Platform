package com.yarasoftware.workshopngine.platform.file.interfaces.rest.resources;

public record FileResource(Long id, String name, String type, Long size, byte[] data) {
}
