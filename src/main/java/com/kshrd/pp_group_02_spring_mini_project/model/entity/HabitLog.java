package com.kshrd.pp_group_02_spring_mini_project.model.entity;

import com.kshrd.pp_group_02_spring_mini_project.model.enums.HabitLogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLog {
    private Integer habitLogId ;
    private LocalDate logDate;
    private HabitLogStatus status ;
    private Integer  xpEarned ;
    private Integer habitId ;
}
