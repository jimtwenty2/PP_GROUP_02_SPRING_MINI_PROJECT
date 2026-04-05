package com.kshrd.pp_group_02_spring_mini_project.security.service;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUserResponse register(RegisterRequest appUserRequest);

    void verifyOtp(String email, String inputOtp) throws BadRequestException;

    void resendOtp(String email);

    boolean isVerifiedByIdentifier(String identifier);
}
