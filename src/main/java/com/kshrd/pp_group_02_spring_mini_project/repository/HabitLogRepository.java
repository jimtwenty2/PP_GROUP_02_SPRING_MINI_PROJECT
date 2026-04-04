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
    @Select("SELECT * FROM habit_logs LIMIT #{size} OFFSET ${(page - 1) * size}")
    List<HabitLog> getAllHabitLog(int page , int size) ;

    @ResultMap("mapper")
    @Select("SELECT * FROM habit_logs")
    List<HabitLog> getAllHabitLogItem();

    @ResultMap("mapper")
    @Select("SELECT * FROM habit_logs where habit_lod_id = #{id}")
    HabitLog getAttendeeByUserId(Integer id);

    @ResultMap("mapper")
    @Select("insert into  habit_logs (log_date, status, xp_earned, habit_id) values (#{logDate} , #{status}) , #{xpEarned} , #{habitId}")
    HabitLog createHabitLog(HabitLogRequest habitLogRequest);

    @ResultMap("mapper")
    @Select("update habit_logs set log_date = #{req.LogDate} , status = #{req.status} , xp_earned = #{req.xpEarned} , habit_log_id = #{req.habitId}  where habit_log_id = #{id} RETURNING *")
    HabitLog updateHabitLog(Long id , @Param("req") HabitLogRequest habitLogRequest);


    @ResultMap("mapper")
    @Select("delete from habit_logs where habit_log_id = #{id}")
    HabitLog deleteHabitlog(Long id);

//    @Select("SELECT * FROM habit_logs where habit_log_id = #{id}")
//    boolean existsById(Long id);
}
