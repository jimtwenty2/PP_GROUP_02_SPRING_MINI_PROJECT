package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitLogRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitLogService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habit-logs")
@RequiredArgsConstructor
public class HabitLogController {
    private final HabitLogService habitLogService;
    @GetMapping("/{habit-id}")
    @Operation(summary = "Create a new habit log by Habit Id")
    public ResponseEntity<ApiResponse<List<HabitLog>>> getAllHabitLogHabitId(@PathVariable("habit-id") UUID habitId , @RequestParam(defaultValue = "1") @Positive(message = "size must be at least 1") int page  , @RequestParam(defaultValue = "10") @Positive(message = "size must be at least 1") int size ){

        List<HabitLog> habitLogs = habitLogService.getAllHabitLog(habitId , page , size);

        ApiResponse<List<HabitLog>> apiResponse = ApiResponse.<List<HabitLog>>builder()
                .success(true)
                .message("fetched all habit logs for the specified habit ID successfully!")
                .status(HttpStatus.OK)
                .payload(habitLogs)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }



    @PostMapping
    public ResponseEntity<ApiResponse<HabitLog>> postHabitLog(@RequestBody HabitLogRequest habitLogRequest) {


        HabitLog habitLog = new HabitLog();
        habitLog.setLogDate(LocalDate.now());
        habitLog.setStatus(habitLogRequest.getStatus());
        habitLog.setXpEarned(10);
        habitLog.setHabitId(habitLogRequest.getHabitId());

        HabitLog logAll = habitLogService.postHabitLog(habitLog);
        ApiResponse<HabitLog> apiResponse = ApiResponse.<HabitLog>builder()
                .success(true)
                .message("Habit log created successfully!")
                .status(HttpStatus.CREATED)
                .payload(logAll)
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
