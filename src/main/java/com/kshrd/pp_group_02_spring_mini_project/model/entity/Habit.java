package com.kshrd.pp_group_02_spring_mini_project.model.entity;
import jdk.jfr.Frequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habit {
    private UUID habitId;
    private String habitTitle;
    private String description;
    private Frequency frequency;

}

