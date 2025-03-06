package ru.aveskin.goal.repository;

import ru.aveskin.goal.model.Goal;
import ru.aveskin.user.model.User;

import java.util.List;

public interface GoalRepository {
    void save(User user, Goal newGoal);

    List<Goal> getGoalsByEmail(String email);
}
