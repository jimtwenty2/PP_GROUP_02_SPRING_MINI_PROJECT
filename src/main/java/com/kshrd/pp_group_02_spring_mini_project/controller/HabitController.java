package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.Habit;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {
    private final HabitService habitService;
    @PostMapping
    public ResponseEntity<?> createHabit(
            @RequestBody HabitRequest request){

        ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Habit created successfully!")
                .payload(habitService.createNewHabit(request))
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHabitById(@PathVariable("id") UUID habitId) {
        ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Habit fetched successfully!")
                .payload(habitService.getHabitById(habitId))
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHabit(
            @PathVariable("id") UUID habitId,
            @RequestBody HabitRequest request) {

        ApiResponse<Habit> response = ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Habit updated successfully!")
                .payload(habitService.updateHabitById(habitId, request))
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
