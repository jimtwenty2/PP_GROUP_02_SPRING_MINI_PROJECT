package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/habit-logs")

public class HabitLogController {
    @GetMapping
    @Operation(summary = "Create a new habit log")
    public ResponseEntity<ApiResponse<HabitLogController>> getAllHabitLog(){

        return ResponseEntity<>;
    }
}
