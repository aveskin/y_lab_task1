package ru.aveskin.goal.service;

import ru.aveskin.user.model.User;

public interface GoalService {
    void createGoal(User user);

    void showGoals(User user);

    void addSavingsToGoal(User user);
}
