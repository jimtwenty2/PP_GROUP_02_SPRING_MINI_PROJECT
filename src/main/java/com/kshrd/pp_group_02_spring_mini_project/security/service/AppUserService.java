package com.kshrd.pp_group_02_spring_mini_project.security.service;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUserResponse register(RegisterRequest appUserRequest);

    boolean verifyOtp(String email, String inputOtp);

    String resendOtp(String email);
}
