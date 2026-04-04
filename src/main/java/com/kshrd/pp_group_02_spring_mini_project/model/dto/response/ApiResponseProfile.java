package com.kshrd.pp_group_02_spring_mini_project.model.dto.response;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseProfile {
    private boolean success;
    private String message;
    private String status;
    private Profile payload;
    private LocalDateTime timestamp;
}
