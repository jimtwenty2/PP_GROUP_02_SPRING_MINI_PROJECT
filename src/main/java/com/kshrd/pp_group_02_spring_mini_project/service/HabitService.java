package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits(@Min(1) Integer page, @Min(1) Integer size);

    Habit deleteHabitById(@Positive(message = "Habit id cannot negative and zero number") UUID habitId);

    Habit getUserByUserId(@Positive(message = "habit id cannot negative and zero number") UUID habitId);
}
