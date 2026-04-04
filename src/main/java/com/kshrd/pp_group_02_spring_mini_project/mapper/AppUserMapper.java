package com.kshrd.pp_group_02_spring_mini_project.mapper;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserResponse mapToAppUserResponse(AppUser appUser);
}
