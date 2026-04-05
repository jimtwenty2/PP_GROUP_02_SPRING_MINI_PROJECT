package com.kshrd.pp_group_02_spring_mini_project.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiVoidResponse{
    private boolean success;
    private String message;
    private HttpStatus status;
    private Instant timestamp;
}
