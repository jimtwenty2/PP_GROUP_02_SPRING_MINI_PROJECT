package com.kshrd.pp_group_02_spring_mini_project.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponse {
    private String fileName;
    private String fileUrl;
    private String fileType;
    private long fileSize;
}
