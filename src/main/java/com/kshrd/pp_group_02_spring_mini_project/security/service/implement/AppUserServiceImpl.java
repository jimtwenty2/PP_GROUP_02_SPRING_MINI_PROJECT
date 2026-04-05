package com.kshrd.pp_group_02_spring_mini_project.security.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.mapper.AppUserMapper;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.repository.AppUserRepository;
import com.kshrd.pp_group_02_spring_mini_project.security.service.AppUserService;
import com.kshrd.pp_group_02_spring_mini_project.service.EmailService;
import com.kshrd.pp_group_02_spring_mini_project.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final RedisTemplate redisTemplate;
    private final OtpService otpService;
    private final EmailService emailService;
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsernameOrEmail(identifier);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with: " + identifier);
        }
        return user;
    }

    @Override
    public AppUserResponse register(RegisterRequest appUserRequest) {
        AppUser appUser = appUserRepository.saveAppUser(appUserRequest);
        AppUserResponse appUserResponse = appUserMapper.mapToAppUserResponse(appUser);
        String email = appUserResponse.getEmail();
        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email,otp);
        return appUserResponse;
    }

    @Override
    public boolean verifyOtp(String email, String inputOtp) {
        boolean isValid = otpService.verifyOtp(email, inputOtp);
        if (!isValid) return false;
        AppUser user = appUserRepository.findByEmail(email);
        if (user != null && !user.isVerified()) {
            user.setVerified(true);
            appUserRepository.updateUserIsVerified(user);
            return true;
        }
        return false;
    }

    @Override
    public String resendOtp(String email) {
        AppUser user = appUserRepository.findByEmail(email);
        System.out.println(user);
        if (user == null) {
            throw new RuntimeException("User with email " + email + " not found");
        }
        System.out.println(user.isVerified());
        if (user.isVerified()) {
            throw new RuntimeException("User with email " + email + " is already verified");
        }
        String newOtp = otpService.resendOtp(email);
        emailService.sendOtpEmail(email, newOtp);
        return newOtp;
    }
}