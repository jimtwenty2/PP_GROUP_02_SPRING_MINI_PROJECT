package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileRequest;
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
    @Override
    public Profile getCurrentUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()){
            return null;
        }
        String userName;
        if (auth.getPrincipal() instanceof UserDetails){
            userName = ((UserDetails) auth.getPrincipal()).getUsername();
        } else {
            userName = auth.getPrincipal().toString();
        }
        return profileMapper.findUserByUserName(userName);
    }

    @Override
    public Profile updateUserProfile(String userName, ProfileRequest profileRequest) {

        return null;
    }
}
