package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Profile;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface ProfileRepository {

    @Select("""
        SELECT app_user_id, username, email, level, xp, profile_image, is_verified, created_at
        FROM app_users
        WHERE username = #{userName}
        """)
    Profile findUserByUserName(String userName);

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
