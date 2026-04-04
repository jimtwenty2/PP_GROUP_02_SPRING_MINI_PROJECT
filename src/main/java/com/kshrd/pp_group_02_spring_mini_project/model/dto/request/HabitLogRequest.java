package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;

import com.kshrd.pp_group_02_spring_mini_project.constants.HabitLogStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogRequest {
//    private Instant logDate;
    @Schema(example = "COMPLETED")
    private HabitLogStatus status ;
//    private Integer  xpEarns ;
    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID habitId ;
}
