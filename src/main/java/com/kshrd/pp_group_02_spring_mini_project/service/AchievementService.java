package com.kshrd.pp_group_02_spring_mini_project.service;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Achievement;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AchievementService {
    List<Achievement> getAddAchievements(Integer page, Integer size);
}
