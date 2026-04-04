package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitLogRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;

import java.util.List;

public interface HabitLogService {

    List<HabitLog> getAllHabitLog(Integer habitId ,int page ,int size) ;
    HabitLog postHabitLog(HabitLog habitLog);
}
