package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitLogRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;

import java.util.List;

public interface HabitLogService {
    List<HabitLog> getAllHabitLog(int page , int size) ;
    HabitLog getHabitLogById(Integer id);
    HabitLog postHabitLog(HabitLogRequest habitLogRequest);
    HabitLog updateHabitLog(Integer id , HabitLogRequest habitLogRequest);
    HabitLog deleteHabitLog(Integer id);

}
