package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface ProfileRepository {


    @Update("""
            UPDATE app_users
            SET username = #{req.userName},
                profile_image = #{req.profileImageUrl}
            WHERE app_user_id = #{userId}
            """)
    void updateUserProfile(@Param("userId") UUID userId, @Param("req") ProfileUpdateRequest profileUpdateRequest);

    @Delete("""
            DELETE FROM app_users
            WHERE app_user_id = #{userId}
            """)
    void deleteUserByUserId(@Param("userId") UUID userId);


}
