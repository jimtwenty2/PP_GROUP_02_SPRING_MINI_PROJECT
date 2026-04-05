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


        if(page <= 0 || size <= 0){
            throw new IllegalArgumentException("page and size must be greater then 0");
        }

        if (page <= 0) {
             throw new NotFoundExceptionHandler("Page number must be greater than 0");
        }

        if (size <= 0) {
            throw  new NotFoundExceptionHandler( "Size number must be greater than 0");
        }
       boolean exists = habitLogRepository.getAllHabitIdsInLogs().contains(habitId);
        if (!exists) {
            throw new NotFoundExceptionHandler("Habit with ID " + habitId + " does not exist.");
        }
        return habitLogRepository.getAllHabitLogByHabitId(habitId, page, size);
    }

    @Override
    public HabitLog postHabitLog(HabitLog habitLog) {
            if (habitLog.getStatus() != HabitLogStatus.COMPLETED) {
                throw new NotFoundExceptionHandler("Status must be (COMPLETED ,MISSED , SKIPPED ");
            }
            return habitLogRepository.insertHabitLog(habitLog);
        }
}
