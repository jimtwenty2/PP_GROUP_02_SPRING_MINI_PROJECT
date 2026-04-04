package com.kshrd.pp_group_02_spring_mini_project.controller;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.request.HabitLogRequest;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.entity.HabitLog;
import com.kshrd.pp_group_02_spring_mini_project.service.HabitLogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Options;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/habit-logs")
@RequiredArgsConstructor
public class HabitLogController {
    private final HabitLogService habitLogService;
    @GetMapping("/{habit-id}")
    @Operation(summary = "Create a new habit log by Habit Id")
    public ResponseEntity<ApiResponse<List<HabitLog>>> getAllHabitLogHabitId(@PathVariable("habit-id") Integer habitId , @RequestParam int page  , @RequestParam int size ){

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
    public ResponseEntity<ApiResponse<HabitLog>> postHabitLog(@RequestBody HabitLogRequest habitLogRequest){

        HabitLog habitLog = new HabitLog();
        Instant date = Instant.now();
        habitLog.setLogDate(date);
        habitLog.setStatus(habitLogRequest.getStatus());
        habitLog.setXpEarned(0);
        habitLog.setHabitId(habitLogRequest.getHabitId());

        HabitLog habitLogs = habitLogService.postHabitLog(habitLog) ;
        ApiResponse<HabitLog> apiResponse = ApiResponse.<HabitLog>builder()
                .success(true)
                .message("create habit log successfully!")
                .status(HttpStatus.OK)
                .payload(habitLogs)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
