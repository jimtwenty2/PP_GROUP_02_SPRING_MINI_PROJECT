package com.kshrd.pp_group_02_spring_mini_project.repository;


import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitLogRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HabitLogRepository {
    @Results(id = "mapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habitId", column = "habit_id")
    })

    @Select("SELECT * FROM habit_logs WHERE habit_id = #{habitId} LIMIT #{size} OFFSET ${(page - 1) * size} ")
    List<HabitLog> getAllHabitLogByHabitId(Integer habitId , int page , int size);

    @ResultMap("mapper")
    @Select("insert into  habit_logs (log_date, status, xp_earned, habit_id) values ( #{logDate} , #{status}::habit_log_status , #{xpEarned} , #{habitId} )")
    HabitLog createHabitLog(HabitLog habitLog);


    @Result(property = "habitId", column = "habit_id")
    @Select("SELECT habit_id FROM habit_logs")
    List<Integer> getAllHabitIdsInLogs();

}
