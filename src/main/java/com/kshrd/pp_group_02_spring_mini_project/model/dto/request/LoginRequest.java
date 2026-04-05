package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Schema(defaultValue = "yourgmail@hrd.com")
    private String identifier;
    @Schema(defaultValue = "yourpassword")
    private String password;
}
