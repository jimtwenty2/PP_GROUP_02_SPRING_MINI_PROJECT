package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;
import com.kshrd.pp_group_02_spring_mini_project.constants.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitRequest {

    private String habitTitle;
    private String description;
    private Frequency frequency;
}
