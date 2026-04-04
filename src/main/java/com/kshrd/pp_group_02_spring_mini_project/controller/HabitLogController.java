package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitLogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/habit-logs")
@RequiredArgsConstructor
public class HabitLogController {
    private final HabitLogService habitLogService;
    @GetMapping
    @Operation(summary = "Create a new habit log")
    public ResponseEntity<ApiResponse<List<HabitLog>>> getAllHabitLog(@RequestParam int page , @RequestParam int size ){
        List<HabitLog> habitLogs = habitLogService.getAllHabitLog(page , size ) ;
        ApiResponse<List<HabitLog>> apiResponse = ApiResponse.<List<HabitLog>>builder()
                .success(true)
                .timestamp(Instant.now())
                .message("Retrieved attendee successfully")
                .status(HttpStatus.OK)
                .payload(habitLogs)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<HabitLog>>
}
