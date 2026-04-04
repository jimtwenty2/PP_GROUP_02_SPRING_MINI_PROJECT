package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitLogRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;
import com.kshrd.pp_group_02_spring_mini_project.repository.HabitLogRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitLogRepository;


    @Override
    public List<HabitLog> getAllHabitLog(int page , int size) {
        return habitLogRepository.getAllHabitLog(page , size);
    }

    @Override
    public HabitLog getHabitLogById(Integer id) {
        return habitLogRepository.getHabitLogByUserId(id);
    }

    @Override
    public HabitLog postHabitLog(HabitLogRequest habitLogRequest) {
        return null;
    }

    @Override
    public HabitLog updateHabitLog(Integer id, HabitLogRequest habitLogRequest) {
        return null;
    }

    @Override
    public HabitLog deleteHabitLog(Integer id) {
        return null;
    }
}
