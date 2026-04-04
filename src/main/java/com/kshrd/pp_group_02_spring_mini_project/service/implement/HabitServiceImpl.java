package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import com.kshrd.pp_group_02_spring_mini_project.repository.HabitRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabits(Integer page, Integer size) {
        return habitRepository.findAllHabits(page, size);
    }

    @Override
    public Habit deleteHabitById(UUID habitId) {
        return habitRepository.deleteHabitById(habitId);
    }

    @Override
    public Habit getUserByUserId(UUID habitId) {
        return habitRepository.findHabitById(habitId);
    }
}
