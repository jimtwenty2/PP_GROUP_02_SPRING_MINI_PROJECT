package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;

import com.kshrd.pp_group_02_spring_mini_project.constants.Frequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Frequency is required")
    private Frequency frequency;
}
