package com.kshrd.pp_group_02_spring_mini_project.repository;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AppUserRepository {
    @Select("""
        SELECT * FROM app_users WHERE 
                                    username = #{username} 
                                   OR email = #{username};
    """)
    AppUser findUserByUsername(String username);
}
