package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.AppUserResponse;
import com.kshrd.pp_group_02_spring_mini_project.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class TestController {
    private final SecurityUtils securityUtils;
    @GetMapping
    public String getAllTest(){
        return "All test successfully";
    }
    @GetMapping("/current-user")
    public AppUserResponse getMe(){
        return securityUtils.getCurrentUser();
    }
}
