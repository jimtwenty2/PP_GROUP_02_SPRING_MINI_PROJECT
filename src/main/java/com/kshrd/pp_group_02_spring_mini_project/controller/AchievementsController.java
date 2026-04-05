package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Achievement;
import com.kshrd.pp_group_02_spring_mini_project.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/achievements")
@RequiredArgsConstructor
public class AchievementsController {

    private final AchievementService achievementService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievements(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Achievement>>builder()
                        .success(true)
                        .message("Achievements retrieved successfully!")
                        .status(HttpStatus.OK)
                        .payload(achievementService.getAddAchievements(page, size))
                        .timestamp(Instant.now())
                        .build()
        );
    }
    @GetMapping("app-users")
    public ResponseEntity<ApiResponse<List<Achievement>>> getAchievementsByAppUsers(){

        return null;
    }
}
