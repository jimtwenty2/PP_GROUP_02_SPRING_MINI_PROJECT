package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.LoginRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiVoidResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.TokenResponse;
import com.kshrd.pp_group_02_spring_mini_project.security.jwt.JwtUtils;
import com.kshrd.pp_group_02_spring_mini_project.security.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest loginRequest) {
        try {
            String identifier = loginRequest.getIdentifier();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            identifier,
                            loginRequest.getPassword()
                    )
            );

            if (!appUserService.isVerifiedByIdentifier(identifier)) {
                throw new BadCredentialsException("NOT_VERIFIED");
            }

            String token = jwtUtils.generateToken(identifier);
            TokenResponse tokenResponse = TokenResponse.builder().token(token).build();
            return ResponseEntity.ok(
                    ApiResponse.<TokenResponse>builder()
                            .success(true)
                            .status(HttpStatus.OK)
                            .message("Login successful!")
                            .payload(tokenResponse)
                            .timestamp(Instant.now())
                            .build());
        } catch (BadCredentialsException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new RuntimeException("An unexpected error occurred during login.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AppUserResponse>> register(@RequestBody @Valid RegisterRequest appUserRequest){

        String rawPwd = appUserRequest.getPassword();
        appUserRequest.setPassword(passwordEncoder.encode(rawPwd));
        AppUserResponse appUserResponse = appUserService.register(appUserRequest);
        ApiResponse<AppUserResponse> appUserResponseApiResponse = ApiResponse
                .<AppUserResponse>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Register new user successfully")
                .payload(appUserResponse)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserResponseApiResponse);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiVoidResponse> verifyOtp(@RequestParam String email, @RequestParam String otp) throws BadRequestException {
        appUserService.verifyOtp(email, otp);
            ApiVoidResponse apiVoidResponse = ApiVoidResponse.builder()
                    .success(true).message("Email successfully verified! You can now log in.")
                    .status(HttpStatus.OK).timestamp(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.OK).body(apiVoidResponse);

    }

    @PostMapping("/resend")
    public ResponseEntity<ApiVoidResponse> resendOtp(@RequestParam String email){
            appUserService.resendOtp(email);
            ApiVoidResponse apiVoidResponse = ApiVoidResponse.builder()
                    .success(true).message("Verification OTP successfully resent to your email.")
                    .status(HttpStatus.CREATED).timestamp(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.OK).body(apiVoidResponse);
    }

}