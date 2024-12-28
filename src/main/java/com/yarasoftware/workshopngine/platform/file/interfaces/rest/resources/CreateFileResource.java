package com.yarasoftware.workshopngine.platform.file.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record CreateFileResource(MultipartFile file) {
}
