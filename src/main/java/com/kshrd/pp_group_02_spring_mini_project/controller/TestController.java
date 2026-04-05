package com.kshrd.pp_group_02_spring_mini_project.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
@SecurityRequirement(name = "bearerAuth")
public class TestController {
    @GetMapping
    public String getAllTest(){
        return "All test successfully";
    }
}
