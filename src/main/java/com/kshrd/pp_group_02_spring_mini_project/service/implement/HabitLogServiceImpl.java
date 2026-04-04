package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.exception.CustomValidationException;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitLogRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;
import com.kshrd.pp_group_02_spring_mini_project.repository.HabitLogRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitLogRepository;

    @Override
    public List<HabitLog> getAllHabitLog(Integer habitId, int page, int size) {
        Map<String, String> errors = new HashMap<>();

        if (page <= 0) errors.put("page", "Page number must be greater than 0");
        if (size <= 0) errors.put("size", "Size number must be greater than 0");

        List<Integer> existingIds = habitLogRepository.getAllHabitIdsInLogs();
        boolean found = false;
        for (Integer id : existingIds) {
            if (id.equals(habitId)) {
                found = true;
                break;
            }
        }
        if (!found) {
            errors.put("habitId", "Habit with ID " + habitId + " does not exist.");
        }
        if (!errors.isEmpty()) {
            throw new CustomValidationException(errors);
        }
        return habitLogRepository.getAllHabitLogByHabitId(habitId, page, size);
    }

    @Override
    public HabitLog postHabitLog(HabitLog habitLog) {
        Map<String, String> errors = new HashMap<>();

//        List<Habit> habit = habitLogRepository.getAllHabits();
//        for(Habit h : habit){
//            if(!h.getHabitId().equals(habitLog)){
//                errors.put("habitId", "Habit with ID " + habitLog.getHabitId() + " does not exist.");
//            }
//        }
        return habitLogRepository.createHabitLog(habitLog);
    }
}
