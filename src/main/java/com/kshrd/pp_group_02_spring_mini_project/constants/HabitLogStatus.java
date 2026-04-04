package com.kshrd.pp_group_02_spring_mini_project.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HabitLogStatus {
        COMPLETED,
        MISSED,
        SKIPPED;

        @JsonCreator
        public static HabitLogStatus from(String value) {
            return HabitLogStatus.valueOf(value.toUpperCase());
        }
    }
