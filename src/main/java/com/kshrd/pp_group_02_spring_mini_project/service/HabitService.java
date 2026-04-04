package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;

import java.util.UUID;

public interface HabitService {
    Habit createNewHabit(HabitRequest request);

    Habit getHabitById(UUID habitId);

    Habit updateHabitById(UUID habitId, HabitRequest request);
}
