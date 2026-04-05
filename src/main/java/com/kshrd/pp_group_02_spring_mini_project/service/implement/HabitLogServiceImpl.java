package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.constants.HabitLogStatus;
import com.kshrd.pp_group_02_spring_mini_project.exception.NotFoundExceptionHandler;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;
import com.kshrd.pp_group_02_spring_mini_project.repository.HabitLogRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitLogRepository;


    @Override
    public List<HabitLog> getAllHabitLog(UUID habitId, int page, int size) {
       boolean exists = habitLogRepository.getAllHabitIdsInLogs().contains(habitId);
        if (!exists) {
            throw new NotFoundExceptionHandler("Habit with ID " + habitId + " does not exist.");
        }
        return habitLogRepository.getAllHabitLogByHabitId(habitId, page, size);
    }

    @Override
    public HabitLog postHabitLog(HabitLog habitLog) {
            if (habitLog.getStatus() != HabitLogStatus.COMPLETED) {
                throw new NotFoundExceptionHandler("Status must be (COMPLETED ,MISSED OR SKIPPED ");
            }
            return habitLogRepository.insertHabitLog(habitLog);
        }
}
