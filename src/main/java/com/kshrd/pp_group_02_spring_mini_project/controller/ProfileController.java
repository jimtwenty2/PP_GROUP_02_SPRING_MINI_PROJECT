package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponseProfile;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Profile;
import com.kshrd.pp_group_02_spring_mini_project.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    ProfileService userProfile;
    @GetMapping("/profile")
    public ApiResponseProfile getProfile(){
        ApiResponseProfile response = ApiResponseProfile.builder()
                .success(true)
                .message("User profile fetched successfully!")
                .status("OK")
                .payload(userProfile.getCurrentUserProfile())
                .timestamp(LocalDateTime.now())
                .build();
        return response;
    }
}
