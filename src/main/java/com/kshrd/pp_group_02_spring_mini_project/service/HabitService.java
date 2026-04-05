package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import jakarta.validation.Valid;


import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabits(Integer page, Integer size);

    Habit getHabitById(UUID habitId);

    Habit deleteHabitById(UUID habitId);

    Habit createHabit(@Valid HabitRequest habitRequest);
}
