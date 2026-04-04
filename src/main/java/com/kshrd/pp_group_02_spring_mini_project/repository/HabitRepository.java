package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HabitRepository {

    @Select("INSERT INTO habits (title, description, frequency, is_active, created_at) " +
            "VALUES (#{req.habitTitle}, #{req.description}, #{req.frequency}::habit_frequency, " +
            "true, CURRENT_TIMESTAMP)")

    Habit saveHabit(@Param("req") HabitRequest request);

    @Select("SELECT * FROM habits WHERE habit_id = #{id}")
    @Results(id = "habitMapping", value = {
            @Result(property = "habitId", column = "habit_id"),
            @Result(property = "habitTitle", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "frequency", column = "frequency"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createdAt", column = "created_at")
    })
    Habit getHabitById(@Param("id") java.util.UUID id);
}
