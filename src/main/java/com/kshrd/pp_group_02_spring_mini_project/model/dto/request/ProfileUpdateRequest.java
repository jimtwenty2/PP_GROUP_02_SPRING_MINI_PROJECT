package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateRequest {
    @NotBlank(message = "userName must not be blank")
    private String userName;
    private String profileImageUrl;
}
