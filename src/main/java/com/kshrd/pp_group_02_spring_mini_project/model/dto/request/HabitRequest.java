package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;
import com.kshrd.pp_group_02_spring_mini_project.constants.Frequency;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitRequest {
    @NotBlank(message = "must not be blank")
    private String title;
    @NotBlank(message = "must not be blank")
    private String description;
    private Frequency frequency;
}
