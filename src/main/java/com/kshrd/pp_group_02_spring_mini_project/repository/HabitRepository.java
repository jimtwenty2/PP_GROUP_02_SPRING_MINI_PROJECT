package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.UUIDTypeHandler;

import java.util.UUID;

@Mapper
public interface HabitRepository {

        @Results(id = "habitMapping", value = {
                @Result(property = "habitId", column = "habit_id", typeHandler = UUIDTypeHandler.class),
                @Result(property = "habitTitle", column = "title"),
                @Result(property = "description", column = "description"),
                @Result(property = "frequency", column = "frequency"),
                @Result(property = "isActive", column = "is_active"),
                @Result(property = "createdAt", column = "created_at")
        })
        @Select("SELECT * FROM habits WHERE habit_id = #{id}")
        Habit getHabitById(@Param("id") UUID id);


        @ResultMap("habitMapping")
        @Select("INSERT INTO habits (title, description, frequency, is_active, created_at) " +
                "VALUES (#{req.habitTitle}, #{req.description}, #{req.frequency}::habit_frequency, " +
                "true, CURRENT_TIMESTAMP) RETURNING *")
        Habit saveHabit(@Param("req") HabitRequest request);

        @ResultMap("habitMapping")
        @Select("UPDATE habits SET title = #{req.habitTitle} WHERE habit_id = #{id} RETURNING *")
        Habit updateHabitByID(@Param("id") UUID id, @Param("req") HabitRequest request);
    }
