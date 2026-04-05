package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.typehandler.UuidTypeHandler;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

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


    @Result(property = "appUserId", column = "app_user_id",
            jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class
    )
    @Result(property = "profileImage", column = "profile_image")
    @Result(property = "isVerified", column = "is_verified")
    @Result(property = "createdAt", column = "created_at")
    @Select("""
                SELECT * FROM app_users WHERE app_user_id = #{userId, jdbcType=OTHER} ;
            """)
    AppUserResponse findByUserId(UUID userId);

    @Select("""
                INSERT INTO app_users (username, email, password ,profile_image) 
                VALUES (#{req.username}, #{req.email}, #{req.password},#{req.profileImageUrl})
                RETURNING *;
            """)
    @ResultMap("appUserMapper")
    AppUser saveAppUser(@Param("req") RegisterRequest appUserRequest);

    @Select("""
                SELECT * FROM app_users WHERE email = #{email};
            """)
    @ResultMap("appUserMapper")
    AppUser findByEmail(String email);

    @Update("UPDATE app_users SET is_verified = #{isVerified} WHERE email = #{email}")
    @ResultMap("appUserMapper")
    void updateUserIsVerified(AppUser user);

    @Select("SELECT count(*) > 0 FROM app_users WHERE email = #{email};")
    boolean findExistenceByEmail(String email);

    @Select("SELECT count(*) > 0 FROM app_users WHERE username = #{username};")
    boolean findExistenceByUsername(String username);

    @Select("SELECT is_verified FROM app_users WHERE username = #{identifier} OR email = #{identifier};")
    boolean checkIsVerifiedByIdentifier(String identifier);

    @Update("""
            UPDATE app_users
            SET xp = #{xpEarned}
            WHERE app_user_id = #{userId, jdbcType=OTHER}
            """)
    void increaseXpToUser(UUID userId, Integer xpEarned);

    @Update("UPDATE app_users SET " +
            "xp = xp + 10, " +
            "level = CASE " +
            "   WHEN (xp + 10) >= 200 THEN 3 " +
            "   WHEN (xp + 10) >= 100 THEN 2 " +
            "   ELSE level " +
            "END " +
            "WHERE app_user_id = #{userId}")
    void updateXpUser(UUID userId);
}
