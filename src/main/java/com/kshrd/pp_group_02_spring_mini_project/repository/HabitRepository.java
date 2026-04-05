package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import com.kshrd.pp_group_02_spring_mini_project.typehandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UuidTypeHandler.class, jdbcType = JdbcType.OTHER),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "appUserResponse", column = "app_user_id",
                    one = @One(select = "com.kshrd.pp_group_02_spring_mini_project.repository.AppUserRepository.findByUserId")
            ),
            @Result(property = "createdAt", column = "created_at")

    })

    @Select("""
        SELECT * FROM habits 
        LIMIT #{size} OFFSET (#{page} -1) * #{size}
    """)
    List<Habit> findAllHabits(Integer page, Integer size);

    @ResultMap("habitMapper")
    @Select("""
        SELECT * FROM habits
        WHERE habit_id = #{habitId}
    """)
    Habit findHabitById(UUID habitId);

    @Select("""
        DELETE FROM habits
        WHERE habit_id = #{habitId}
    """)
    Habit deleteHabitById(UUID habitId);
}
