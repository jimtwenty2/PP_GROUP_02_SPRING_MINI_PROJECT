package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.model.entity.Achievement;
import com.kshrd.pp_group_02_spring_mini_project.repository.AchievementRepository;
import com.kshrd.pp_group_02_spring_mini_project.service.AchievementService;
import com.kshrd.pp_group_02_spring_mini_project.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final SecurityUtils securityUtils;

    @Override
    public List<Achievement> getAddAchievements(Integer page, Integer size) {
        return achievementRepository.getAllAchievements(page, size);
    }

    @Override
    public List<Achievement> getAllAchievementsByAppUsers(Integer page, Integer size) {

        String userID = securityUtils.getCurrentUser().getAppUserId().toString();

        return achievementRepository.getAllAchievementByUserId(userID, page, size);
    }
}
