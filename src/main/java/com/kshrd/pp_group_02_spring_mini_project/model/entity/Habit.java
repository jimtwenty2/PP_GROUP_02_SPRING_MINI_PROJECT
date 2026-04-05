package com.kshrd.pp_group_02_spring_mini_project.model.entity;

import com.kshrd.pp_group_02_spring_mini_project.constants.Frequency;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habit {
    private UUID habitId;
    @NotBlank(message = "title must not be blank")
    private String title;
    @NotBlank(message = "description must not be blank")
    private String description;
    private Frequency frequency;
    private List<AppUserResponse> appUser;
    private boolean isActive;
    private AppUserResponse appUserResponse;
    private Instant createdAt;
}
