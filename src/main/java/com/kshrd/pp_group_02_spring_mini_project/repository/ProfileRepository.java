package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface ProfileRepository {

    @Select("""
        SELECT app_user_id, username, email, level, xp, profile_image, is_verified, created_at
        FROM app_users
        WHERE username = #{userName}
        """)
    AppUserResponse findUserByUserName(String userName);

    @Update("""
            UPDATE app_users
            SET username = #{req.userName},
                profile_image = #{req.imgUrl}
            WHERE username = #{userName}
            """)
    void updateUserProfile(@Param("userName") String userName, @Param("req") ProfileUpdateRequest profileUpdateRequest);

    @Delete("""
            DELETE FROM app_users
            WHERE username = #{userName}
            """)
    void deleteUserByUserName(@Param("userName") String userName);


}
