package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.mapper.AppUserMapper;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.repository.AppUserRepository;
import com.kshrd.pp_group_02_spring_mini_project.repository.ProfileRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileMapper;
    private final AppUserMapper appUserMapper;
    private final AppUserRepository appUserRepository;

    public String getCurrentUserName(){
        String userName;
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUserResponse appUserResponse = appUserMapper.mapToAppUserResponse(currentUser);
        return userName = appUserResponse.getUsername();
    }

//    @Override
//    public AppUserResponse getCurrentUserProfile() {
//        String userName = getCurrentUserName();
//
//        AppUserResponse profile = profileMapper.findUserByUserName(userName);
//        if (profile == null) {
//            throw new RuntimeException("User not found");
//        }
//        return profile;
//    }

    @Override
    public AppUserResponse getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        String userName = authentication.getName();

        AppUser user = null;

        Optional<AppUser> result = Optional.ofNullable(appUserRepository.findByUsernameOrEmail(userName));
        if (result.isPresent()){
            user = result.get();
        }else {
            throw new RuntimeException("User not found");
        }
        return appUserMapper.mapToAppUserResponse(user);
    }

    @Override
    public AppUserResponse updateUserProfile(ProfileUpdateRequest profileUpdateRequest) {
        String userName = getCurrentUserName();

        AppUserResponse profile = profileMapper.findUserByUserName(userName);

        if (profile == null) {
            throw new RuntimeException("User not found");
        }
        profileMapper.updateUserProfile(userName,profileUpdateRequest);

        return profileMapper.findUserByUserName(userName);
    }

    @Override
    public void deleteCurrentUser() {
        String userName = getCurrentUserName();

        AppUserResponse profile = profileMapper.findUserByUserName(userName);

        if (profile == null) {
            throw new RuntimeException("User not found");
        }
        profileMapper.deleteUserByUserName(userName);
    }
}
