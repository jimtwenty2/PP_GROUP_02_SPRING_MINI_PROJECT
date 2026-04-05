package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Profile;
import com.kshrd.pp_group_02_spring_mini_project.repository.ProfileRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileMapper;

    private String getCurrentUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("User not authenticated");
        }
        if (auth.getPrincipal() instanceof  UserDetails){
            return ((UserDetails) auth.getPrincipal()).getUsername();
        }
        return auth.getName();
    }
    @Override
    public Profile getCurrentUserProfile() {
        String userName = getCurrentUserName();

        Profile profile = profileMapper.findUserByUserName(userName);
        if (profile == null) {
            throw new RuntimeException("User not found");
        }
        return profile;
    }

    @Override
    public Profile updateUserProfile(ProfileUpdateRequest profileUpdateRequest) {
        String userName = getCurrentUserName();

        Profile profile = profileMapper.findUserByUserName(userName);

        if (profile == null) {
            throw new RuntimeException("User not found");
        }
        profileMapper.updateUserProfile(userName,profileUpdateRequest);

        return profileMapper.findUserByUserName(userName);
    }

    @Override
    public void deleteCurrentUser() {
        String userName = getCurrentUserName();

        Profile profile = profileMapper.findUserByUserName(userName);

        if (profile == null) {
            throw new RuntimeException("User not found");
        }
        profileMapper.deleteUserByUserName(userName);
    }
}
