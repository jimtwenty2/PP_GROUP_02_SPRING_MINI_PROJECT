package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Achievement;
import com.kshrd.pp_group_02_spring_mini_project.service.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/achievements")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AchievementsController {

    private final AchievementService achievementService;

    @Operation(summary = "Get all achievements"
    , description = "Fetches a paginated list of all achievements.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievements(
            @RequestParam @Positive Integer page,
            @RequestParam @Positive Integer size){
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

    @Operation(summary = "Get achievements by App User ID",
            description = "Fetches a paginated list of achievements filtered by a specific App User ID.")
    @GetMapping("app-users")
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievementsByAppUsers(
            @RequestParam @Positive Integer page,
            @RequestParam @Positive Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Achievement>>builder()
                        .success(true)
                        .message("Achievements for the specified App User retrieved successfully!")
                        .status(HttpStatus.OK)
                        .payload(achievementService.getAllAchievementsByAppUsers(page, size))
                        .timestamp(Instant.now())
                        .build()
        );
    }
}
