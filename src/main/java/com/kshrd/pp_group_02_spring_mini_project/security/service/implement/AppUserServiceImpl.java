package com.kshrd.pp_group_02_spring_mini_project.security.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.exception.AlreadyExistsException;
import com.kshrd.pp_group_02_spring_mini_project.exception.NotFoundExceptionHandler;
import com.kshrd.pp_group_02_spring_mini_project.mapper.AppUserMapper;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.AppUser;
import com.kshrd.pp_group_02_spring_mini_project.repository.AppUserRepository;
import com.kshrd.pp_group_02_spring_mini_project.security.service.AppUserService;
import com.kshrd.pp_group_02_spring_mini_project.service.EmailService;
import com.kshrd.pp_group_02_spring_mini_project.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
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
        if(appUserRepository.findExistenceByUsername(appUserRequest.getUsername())){
            throw new AlreadyExistsException("The username is already associated with an existing account. Please try with a different one.");
        }
        if(appUserRepository.findExistenceByEmail(appUserRequest.getEmail())){
            throw new AlreadyExistsException("The email is already associated with an existing account. Please try with a different one.");
        }
        AppUser appUser = appUserRepository.saveAppUser(appUserRequest);
        AppUserResponse appUserResponse = appUserMapper.mapToAppUserResponse(appUser);
        String email = appUserResponse.getEmail();
        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email,otp,appUser.getUsername());
        return appUserResponse;
    }
    @Override
    public void verifyOtp(String email, String inputOtp) throws BadRequestException {
        if (inputOtp == null || !inputOtp.matches("\\d{6}")) {
            throw new BadRequestException("Invalid OTP format. The code must be exactly 6 digits and cannot be negative.");
        }
        if (!appUserRepository.findExistenceByEmail(email)) {
            throw new NotFoundExceptionHandler("he email address provided is not registered. Please check and try again.");
        }
        boolean isValid = otpService.verifyOtp(email, inputOtp);
        if (!isValid) {
            throw new BadRequestException("The OTP entered is invalid or has expired. Please request a new OTP and try again.");
        }
        AppUser user = appUserRepository.findByEmail(email);
        if (user != null && !user.isVerified()) {
            user.setVerified(true);
            appUserRepository.updateUserIsVerified(user);
        }
    }
    @Override
    public void resendOtp(String email) {
        if (!appUserRepository.findExistenceByEmail(email)) {
            throw new NotFoundExceptionHandler("The email address provided is not registered. Please check and try again.");
        }
        AppUser user = appUserRepository.findByEmail(email);
        if (user.isVerified()) {
            throw new RuntimeException("User with email " + email + " is already verified");
        }
        String newOtp = otpService.resendOtp(email);
        emailService.sendOtpEmail(email, newOtp,user.getUsername());
    }
    @Override
    public boolean isVerifiedByIdentifier(String identifier) {
        return appUserRepository.checkIsVerifiedByIdentifier(identifier);
    }
}