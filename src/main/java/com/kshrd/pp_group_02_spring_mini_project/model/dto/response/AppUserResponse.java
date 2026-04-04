package com.kshrd.pp_group_02_spring_mini_project.model.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class AppUserResponse {
    private UUID appUserId;
    private String username;
    private String email;
    private Integer level;
    private Integer xp;
    private String profileImage;
    private Timestamp createdAt;
    private boolean isVerified;
}
