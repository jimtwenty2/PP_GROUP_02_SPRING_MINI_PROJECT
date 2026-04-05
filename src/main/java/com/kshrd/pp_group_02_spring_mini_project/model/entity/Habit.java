package com.kshrd.pp_group_02_spring_mini_project.model.entity;

import com.kshrd.pp_group_02_spring_mini_project.constants.Frequency;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habit {

    private UUID habitId;
    private String title;
    private String description;
    private Frequency frequency;
    private boolean isActive;
    private AppUserResponse appUserResponse;
    private Instant createAt;
}
