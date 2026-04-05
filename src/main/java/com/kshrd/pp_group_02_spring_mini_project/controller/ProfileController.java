package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponseProfile;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Profile;
import com.kshrd.pp_group_02_spring_mini_project.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public ApiResponseProfile getProfile(){
        Profile profile = profileService.getCurrentUserProfile();
        ApiResponseProfile response = ApiResponseProfile.builder()
                .success(true)
                .message("User profile fetched successfully!")
                .status("OK")
                .payload(profile)
                .timestamp(LocalDateTime.now())
                .build();
        return response;
    }

    @PutMapping("/update")
    public ApiResponseProfile updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest){
        Profile updatedProfile = profileService.updateUserProfile(profileUpdateRequest);
        return ApiResponseProfile.builder()
                .success(true)
                .message("User profile updated successfully")
                .status("OK")
                .payload(updatedProfile)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponseProfile deleteProfile(){
        profileService.deleteCurrentUser();
        return ApiResponseProfile.builder()
                .success(true)
                .message("User profile deleted successfully!")
                .status("OK")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
