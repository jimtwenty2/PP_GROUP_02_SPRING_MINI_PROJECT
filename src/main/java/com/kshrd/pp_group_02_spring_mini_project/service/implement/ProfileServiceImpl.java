package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Profile;
import com.kshrd.pp_group_02_spring_mini_project.repository.ProfileRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ProfileServiceImpl implements ProfileService {
    ProfileRepository profileMapper;
    @Override
    public Profile getCurrentUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) auth.getPrincipal();
        return profileMapper.findUserByUserName(userName);
    }
}
