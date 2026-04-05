package com.kshrd.pp_group_02_spring_mini_project.model.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private String type;
    private String instance;
    private int status;
    private String title;
    private LocalDateTime timestamp;
    private Map<String, String> errors;
}