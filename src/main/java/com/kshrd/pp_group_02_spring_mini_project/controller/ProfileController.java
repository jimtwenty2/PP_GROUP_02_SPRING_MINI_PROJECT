package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.ProfileUpdateRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("api/v1/profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ApiResponse<AppUserResponse> getProfile(){
        AppUserResponse profile = profileService.getCurrentUserProfile();
        return ApiResponse.<AppUserResponse>builder()
                .success(true)
                .message("User profile fetched successfully!")
                .status(HttpStatus.OK)
                .payload(profile)
                .timestamp(Instant.now())
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<AppUserResponse> updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest){
        AppUserResponse updatedProfile = profileService.updateUserProfile(profileUpdateRequest);
        return ApiResponse.<AppUserResponse>builder()
                .success(true)
                .message("User profile updated successfully")
                .status(HttpStatus.OK)
                .payload(updatedProfile)
                .timestamp(Instant.now())
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteProfile(){
        profileService.deleteCurrentUser();
        return ApiResponse.<Void>builder()
                .success(true)
                .message("User profile deleted successfully!")
                .status(HttpStatus.OK)
                .timestamp(Instant.now())
                .build();
    }
}
