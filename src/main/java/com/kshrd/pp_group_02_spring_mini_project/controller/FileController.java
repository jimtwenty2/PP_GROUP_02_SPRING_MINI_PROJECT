package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.FileResponse;
import com.kshrd.pp_group_02_spring_mini_project.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URLConnection;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @PostMapping(value = "upload-file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String fileName = fileService.uploadFile(file);
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/files/preview-file/" + fileName)
                .toUriString();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                        .success(true)
                        .message("File uploaded successfully to RustFS")
                        .status(HttpStatus.CREATED)
                        .payload(FileResponse.builder()
                                .fileName(fileName)
                                .fileUrl(fileUrl)
                                .fileType(file.getContentType())
                                .fileSize(file.getSize())
                                .build())
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @GetMapping("/preview-file/{fileName}")
    public ResponseEntity<?> previewFileByFileName(@PathVariable String fileName) throws IOException {
        Resource resource = fileService.getFileByFileName(fileName);
        String contentType = URLConnection.guessContentTypeFromName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
