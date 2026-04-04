package com.kshrd.pp_group_02_spring_mini_project.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
    private String token;
}
