package com.kshrd.pp_group_02_spring_mini_project.model.entity;

import com.kshrd.pp_group_02_spring_mini_project.constants.HabitLogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLog {
    private UUID habitLogId ;
    private Instant logDate ;
    private HabitLogStatus status ;
    private Integer  xpEarned;
    private Integer habitId ;
}
