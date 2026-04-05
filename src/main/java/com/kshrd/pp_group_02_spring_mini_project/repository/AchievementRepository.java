package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Achievement;
import com.kshrd.pp_group_02_spring_mini_project.typehandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface AchievementRepository {

    @Results(id = "AchievementMapping", value = {
            @Result(property = "achievementId", column = "achievement_id", jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
            @Result(property = "xpRequired", column = "xp_required"),
    })
    @Select("""
        SELECT * FROM achievements
        LIMIT #{size}
        OFFSET (#{page} - 1) * #{size}
    """)
    List<Achievement> getAllAchievements(Integer page, Integer size);

    @ResultMap("AchievementMapping")
    @Select("""
        SELECT * FROM achievements a
        INNER JOIN app_user_achievements aua
        ON a.achievement_id = aua.achievement_id
        WHERE aua.app_user_id = #{userID}::uuid
        LIMIT #{size}
        OFFSET (#{page} - 1) * #{size}
    """)
    List<Achievement> getAllAchievementByUserId(String userID, Integer page, Integer size);
}
