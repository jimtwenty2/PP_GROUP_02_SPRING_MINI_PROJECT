package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits(Integer page, Integer size);

    Habit deleteHabitById(UUID habitId);

    Habit getHabitById(UUID habitId);
}
