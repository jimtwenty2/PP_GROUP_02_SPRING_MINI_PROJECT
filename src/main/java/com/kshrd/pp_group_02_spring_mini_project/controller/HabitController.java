package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/habits")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class HabitController {
    private final HabitService habitService;

    @GetMapping
    @Operation(summary = "Get all habit")
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabits(
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size
    ){

        return ResponseEntity.ok(ApiResponse.<List<Habit>>builder()
                .success(true)
                .message("Fetched all habits successfully!")
                .status(HttpStatus.OK)
                .payload(habitService.getAllHabits(page, size))
                .timestamp(Instant.now())
                .build()
        );
    }

    @GetMapping("/{habit-id}")
    @Operation(summary = "Get habit by ID")
    public ResponseEntity<ApiResponse<Habit>> getHabitById(@PathVariable("habit-id") UUID habitId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Fetched all habits successfully!")
                        .status(HttpStatus.OK)
                        .payload(habitService.getHabitById(habitId))
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @DeleteMapping("/{habit-id}")
    @Operation(summary = "Delete habit by ID")
    public ResponseEntity<ApiResponse<Habit>> deleteHabitById(@PathVariable("habit-id") UUID habitId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Habit deleted successfully!")
                        .status(HttpStatus.OK)
                        .payload(habitService.deleteHabitById(habitId))
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @PostMapping
    @Operation(summary = "Create a new habit")
    public ResponseEntity<ApiResponse<Habit>> createHabit(
            @Valid @RequestBody HabitRequest habitRequest,
            @RequestParam UUID userId
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Habit created successfully!")
                        .status(HttpStatus.CREATED)
                        .payload(habitService.createHabit(habitRequest, userId))
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @PutMapping("/{habit-id}")
    @Operation(summary = "Update habit by ID")
    public ResponseEntity<ApiResponse<Habit>> updateHabit(
            @PathVariable("habit-id") UUID habitId,
            @RequestBody Habit habit
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .success(true)
                        .message("Habit created successfully!")
                        .status(HttpStatus.OK)
                        .payload(habitService.updateHabit(habit, habitId))
                        .timestamp(Instant.now())
                        .build()
        );
    }
}
