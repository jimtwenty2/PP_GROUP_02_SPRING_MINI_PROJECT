package com.kshrd.pp_group_02_spring_mini_project.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogRequest {
//    private Instant logDate;
    @Schema(example = "COMPLETED")
    private String status ;
//    private Integer  xpEarns ;
    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private Integer habit_id ;
}
