package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Achievement;
import com.kshrd.pp_group_02_spring_mini_project.repository.AchievementRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public List<Achievement> getAddAchievements(Integer page, Integer size) {
        return achievementRepository.getAllAchievements(page, size);
    }
}
