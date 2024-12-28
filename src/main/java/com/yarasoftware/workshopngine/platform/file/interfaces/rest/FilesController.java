package com.yarasoftware.workshopngine.platform.file.interfaces.rest;

import com.yarasoftware.workshopngine.platform.file.domain.model.queries.GetFileByIdQuery;
import com.yarasoftware.workshopngine.platform.file.domain.services.FileCommandService;
import com.yarasoftware.workshopngine.platform.file.domain.services.FileQueryService;
import com.yarasoftware.workshopngine.platform.file.interfaces.rest.resources.CreateFileResource;
import com.yarasoftware.workshopngine.platform.file.interfaces.rest.resources.UploadedFileResource;
import com.yarasoftware.workshopngine.platform.file.interfaces.rest.transform.CreateFileCommandFromResourceAssembler;
import com.yarasoftware.workshopngine.platform.file.interfaces.rest.transform.FileResourceFromEntityAssembler;
import com.yarasoftware.workshopngine.platform.file.interfaces.rest.transform.UploadedFileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/files")
@Tag(name = "Files", description = "File Management Endpoints")
public class FilesController {
    private final FileCommandService fileCommandService;
    private final FileQueryService fileQueryService;

    public FilesController(FileCommandService fileCommandService, FileQueryService fileQueryService) {
        this.fileCommandService = fileCommandService;
        this.fileQueryService = fileQueryService;
    }

    @GetMapping("/{fileId}")
    @Operation(summary = "Get a file by id", description = "Get a file by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File found"),
            @ApiResponse(responseCode = "404", description = "File not found")
    })
    public ResponseEntity<byte[]> getFileById(@PathVariable long fileId) {
        var getFileByIdQuery = new GetFileByIdQuery(fileId);
        var file = fileQueryService.handle(getFileByIdQuery);
        if (file.isEmpty()) return ResponseEntity.notFound().build();
        var fileResource = FileResourceFromEntityAssembler.toResourceFromEntity(file.get());
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.name() + "\"")
                .contentType(MediaType.parseMediaType(fileResource.type()))
                .body(fileResource.data());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a file", description = "Create a file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "File created"),
            @ApiResponse(responseCode = "400", description = "File not created")
    })
    public ResponseEntity<UploadedFileResource> createFile(@RequestParam("file") MultipartFile file) throws IOException {
        var resourceFile = new CreateFileResource(file);
        var createFileCommand = CreateFileCommandFromResourceAssembler.toCommandFromResource(resourceFile);
        var fileToCreated = fileCommandService.handle(createFileCommand);
        if (fileToCreated.isEmpty()) return ResponseEntity.badRequest().build();
        var uploadedFileResource = UploadedFileResourceFromEntityAssembler.toResourceFromEntity(fileToCreated.get());
        return new ResponseEntity<>(uploadedFileResource, HttpStatus.CREATED);
    }
}
