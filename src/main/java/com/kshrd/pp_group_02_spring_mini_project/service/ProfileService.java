package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Profile;
import org.apache.ibatis.annotations.Param;

public interface ProfileService {
    Profile getCurrentUserProfile();

    Profile updateUserProfile(ProfileUpdateRequest profileUpdateRequest);
    void deleteCurrentUser();
}
