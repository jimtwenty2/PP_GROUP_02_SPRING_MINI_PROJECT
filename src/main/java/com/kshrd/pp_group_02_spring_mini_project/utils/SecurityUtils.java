package com.kshrd.pp_group_02_spring_mini_project.utils;

import com.kshrd.pp_group_02_spring_mini_project.mapper.AppUserMapper;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final AppUserMapper appUserMapper;

    public AppUserResponse getCurrentUser(){
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUserResponse appUserResponse = appUserMapper.mapToAppUserResponse(currentUser);
        return appUserResponse;
    }
}
