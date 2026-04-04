package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/habit")
@RequiredArgsConstructor
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
    public ResponseEntity<ApiResponse<Habit>> getUserById(@PathVariable("habit-id") @Positive(message = "Habit id cannot negative and zero number") UUID habitId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .timestamp(Instant.now())
                        .message("Get habit with ID " + habitId + " successfully")
                        .payload(habitService.getUserByUserId(habitId))
                        .build()
        );
    }


    @DeleteMapping("/{habit-id}")
    @Operation(summary = "Delete habit by ID")
    public ResponseEntity<ApiResponse<Habit>> deleteUserById(@PathVariable("habit-id") @Positive(message = "Habit id cannot negative and zero number") UUID habitId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Habit>builder()
                        .timestamp(Instant.now())
                        .message("Delete habit with ID " + habitId + " successfully")
                        .payload(habitService.deleteHabitById(habitId))
                        .build()
        );
    }
}
