package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.repository.AppUserRepository;
import com.kshrd.pp_group_02_spring_mini_project.repository.ProfileRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.ProfileService;
import com.kshrd.pp_group_02_spring_mini_project.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final AppUserRepository appUserRepository;
    private final SecurityUtils securityUtils;

    public UUID getCurrentUserId(){
        return securityUtils.getCurrentUser().getAppUserId();
    }



    @Override
    public AppUserResponse getCurrentUserProfile() {

        AppUserResponse currentUser = appUserRepository.findByUserId(getCurrentUserId());
        return currentUser;
    }

    @Override
    public AppUserResponse updateUserProfile(ProfileUpdateRequest profileUpdateRequest) {

        profileRepository.updateUserProfile(getCurrentUserId(),profileUpdateRequest);

        return appUserRepository.findByUserId(getCurrentUserId());
    }

    @Override
    public void deleteCurrentUser() {
        profileRepository.deleteUserByUserId(getCurrentUserId());
    }
}
