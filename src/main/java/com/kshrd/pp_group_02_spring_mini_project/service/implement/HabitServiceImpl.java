package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.exception.NotFoundExceptionHandler;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitRequest;
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
    public Habit getHabitById(UUID habitId) {
        Habit habit = habitRepository.findHabitById(habitId);
        if(habit == null){
            throw new NotFoundExceptionHandler("Habit with ID " + habitId + " not found");
        }
        return habit;
    }

    @Override
    public Habit deleteHabitById(UUID habitId) {
        Habit habit = habitRepository.deleteHabitById(habitId);
        if(habit == null){
            throw new NotFoundExceptionHandler("Habit with ID " + habitId + " not found");
        }
        return habit;
    }

    @Override
    public Habit createHabit(HabitRequest habitRequest, UUID userId) {
        return null;
    }

    @Override
    public Habit updateHabit(Habit habit, UUID habitId) {
        return null;
    }


}
