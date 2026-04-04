package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.typehandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface AppUserRepository {
    @Results(id = "appUserMapper", value = {
            @Result(property = "appUserId", column = "app_user_id",
                    jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class
            ),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
        SELECT * FROM app_users WHERE email = #{identifier} OR username = #{identifier};
    """)
    AppUser findByUsernameOrEmail(String identifier);

    @Select("""
        INSERT INTO app_users (username, email, password ,profile_image) 
        VALUES (#{req.username}, #{req.email}, #{req.password},#{req.profileImageUrl})
        RETURNING *;
    """)
    @ResultMap("appUserMapper")
    AppUser saveAppUser(@Param("req") RegisterRequest appUserRequest);
}
