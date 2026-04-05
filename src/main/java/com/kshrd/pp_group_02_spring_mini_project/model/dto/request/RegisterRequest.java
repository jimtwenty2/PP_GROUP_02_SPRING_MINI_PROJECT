package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    @NotBlank(message = "must not be blank")
    @Email(message = "must be a well-formed email address")
    private String email;
    @NotBlank(message = "must not be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase, one lowercase, one number, and one special character"
    )
    @Schema(defaultValue = "Iam@Y1sFefc")
    private String password;
    private String profileImageUrl;
}
