package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;

public interface ProfileService {
    AppUserResponse getCurrentUserProfile();

    AppUserResponse updateUserProfile(ProfileUpdateRequest profileUpdateRequest);
    void deleteCurrentUser();
}
