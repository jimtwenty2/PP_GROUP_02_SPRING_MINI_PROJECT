package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface ProfileRepository {

    @Select("""
        SELECT * FROM app_users 
        WHERE username = #{userName} 
        """)
    Profile findUserByUserName(String userName);


}
