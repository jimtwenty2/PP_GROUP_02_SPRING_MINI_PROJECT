package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import com.kshrd.pp_group_02_spring_mini_project.repository.HabitRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {
    private final HabitRepository habitRepository;
    @Override
    public Habit createNewHabit(HabitRequest request) {

        System.out.println(request);

        return habitRepository.saveHabit(request);

    }

    @Override
    public Habit getHabitById(UUID habitId) {
        return habitRepository.getHabitById(habitId);
    }

    @Override
    public Habit updateHabitById(UUID habitId, HabitRequest request) {
        return habitRepository.updateHabitByID(habitId,request);
    }

}
