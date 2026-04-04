package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
}
