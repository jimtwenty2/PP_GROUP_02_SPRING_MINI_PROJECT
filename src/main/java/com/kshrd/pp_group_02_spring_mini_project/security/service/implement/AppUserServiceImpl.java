package com.kshrd.pp_group_02_spring_mini_project.security.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.mapper.AppUserMapper;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.repository.AppUserRepository;
import com.kshrd.pp_group_02_spring_mini_project.security.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsernameOrEmail(identifier);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with: " + identifier);
        }
        return user;
    }

    @Override
    public AppUserResponse register(RegisterRequest appUserRequest) {
        AppUser appUser = appUserRepository.saveAppUser(appUserRequest);
        AppUserResponse appUserResponse = appUserMapper.mapToAppUserResponse(appUser);
        return appUserResponse;
    }
}