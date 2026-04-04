package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogRequest {
    private Instant logDate;
    private String status ;
    private Integer  xpEarns ;
    private Integer habit_id ;
}
