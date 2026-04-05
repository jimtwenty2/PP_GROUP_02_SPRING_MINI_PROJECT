package com.kshrd.pp_group_02_spring_mini_project.repository;


import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;
import com.kshrd.pp_group_02_spring_mini_project.typehandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    @Results(id = "mapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habitId", column = "habit_id", typeHandler = UuidTypeHandler.class) ,
            @Result(property = "habitList" , column = "habit_id",
                    one = @One(select = ("com.kshrd.pp_group_02_spring_mini_project.repository.HabitRepository.findHabitById"))
            )

    })
    @Select("""
    SELECT * FROM habit_logs
    WHERE habit_id = #{habitId, jdbcType=OTHER, typeHandler=com.kshrd.pp_group_02_spring_mini_project.typehandler.UuidTypeHandler}
    LIMIT #{size}
    OFFSET #{offset}
""")
    List<HabitLog> getAllHabitLogByHabitId(
            @Param("habitId") UUID habitId,
            @Param("offset") int offset,
            @Param("size") int size
    );


    @ResultMap("mapper")
    @Select("SELECT  * FROM habit_logs")
    HabitLog getAllHabitLog();

    @Select("""
    INSERT INTO habit_logs (log_date, status, xp_earned, habit_id)
    VALUES (#{logDate}, #{status}::habit_log_status, #{xpEarned}, #{habitId, jdbcType=OTHER})
    RETURNING *
""")
    @ResultMap("mapper")
    HabitLog insertHabitLog(HabitLog habitLog);


    @Result(property = "habitId", column = "habit_id", jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class)
    @Select("SELECT habit_id FROM habit_logs")
    List<UUID> getAllHabitIdsInLogs();

}
