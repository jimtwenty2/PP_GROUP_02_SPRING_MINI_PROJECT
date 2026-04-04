package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.LoginRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.RegisterRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.TokenResponse;
import com.kshrd.pp_group_02_spring_mini_project.security.jwt.JwtUtils;
import com.kshrd.pp_group_02_spring_mini_project.security.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getIdentifier(),
                            loginRequest.getPassword()
                    )
            );
            String token = jwtUtils.generateToken(
                    loginRequest.getIdentifier()
            );
            TokenResponse tokenResponse = TokenResponse.builder().token(token).build();
            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiResponse.<TokenResponse>builder()
                            .success(true)
                            .status(HttpStatus.OK)
                            .message("Login successful! Authentication token generated.")
                            .payload(tokenResponse)
                            .timestamp(Instant.now())
                            .build());
        } catch (Exception exception) {
            System.out.println("Login error: " + exception.getMessage());
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AppUserResponse>> register(@RequestBody RegisterRequest appUserRequest){
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

}