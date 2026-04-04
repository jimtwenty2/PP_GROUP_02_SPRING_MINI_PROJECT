package com.kshrd.pp_group_02_spring_mini_project.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum HabitLogStatus {
        COMPLETED,
        MISSED,
        SKIPPED;

    @JsonCreator
    public static HabitLogStatus fromValue(String value) {
        try {
            return HabitLogStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // Return null so it passes deserialization but fails your validation
        }
    }
    }
