package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import com.kshrd.pp_group_02_spring_mini_project.typehandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {

    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UuidTypeHandler.class),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "frequency", column = "frequency"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "appUser", column = "app_user_id",
                    one = @One(select = "com.kshrd.pp_group_02_spring_mini_project.repository.AppUserRepository.appUserId")
            ),
            @Result(property = "creatAt", column = "create_at")

    })

    @Select("""
        SELECT * FROM habits 
        LIMIT #{size} OFFSET (#{page} -1) * #{size}
    """)
    List<Habit> findAllHabits(Integer page, Integer size);

    @Select("""
        DELETE FROM habits
        WHERE habit_id = #{habitId}
    """)
    Habit deleteHabitById(UUID habitId);

    @ResultMap("habitMapper")
    @Select("""
        SELECT * FROM habits
        WHERE habit_id = #{habitId}
    """)
    Habit findHabitById(UUID habitId);
}
