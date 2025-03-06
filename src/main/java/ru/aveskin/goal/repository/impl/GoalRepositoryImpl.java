package ru.aveskin.goal.repository.impl;

import ru.aveskin.goal.model.Goal;
import ru.aveskin.goal.repository.GoalRepository;
import ru.aveskin.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoalRepositoryImpl implements GoalRepository {
    private final Map<String, List<Goal>> usersGoals = new HashMap<>();

    @Override
    public void save(User user, Goal newGoal) {
        usersGoals
                .computeIfAbsent(user.getEmail(), k -> new ArrayList<>())
                .add(newGoal);
    }

    @Override
    public List<Goal> getGoalsByEmail(String email) {
        return usersGoals.getOrDefault(email, new ArrayList<>());
    }
}
