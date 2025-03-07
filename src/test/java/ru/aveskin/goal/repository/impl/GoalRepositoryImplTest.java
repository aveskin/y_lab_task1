package ru.aveskin.goal.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aveskin.goal.model.Goal;
import ru.aveskin.user.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoalRepositoryImplTest {

    private GoalRepositoryImpl goalRepository;
    private User user;

    @BeforeEach
    void setUp() {
        goalRepository = new GoalRepositoryImpl();
        user = new User("test", "test@example.com", "password", false);
    }

    @Test
    void testSaveGoal() {
        Goal goal = new Goal("Сохранить на машину", 100000.0);

        goalRepository.save(user, goal);

        List<Goal> goals = goalRepository.getGoalsByEmail(user.getEmail());

        assertEquals(1, goals.size());
        assertEquals("Сохранить на машину", goals.get(0).getName());
        assertEquals(100000.0, goals.get(0).getTargetAmount(), 0.01);
    }

    @Test
    void testGetGoalsByEmail_noGoals() {
        List<Goal> goals = goalRepository.getGoalsByEmail(user.getEmail());

        assertTrue(goals.isEmpty());
    }

    @Test
    void testGetGoalsByEmail_multipleGoals() {
        Goal goal1 = new Goal("Цель 1", 5000.0);
        Goal goal2 = new Goal("Цель 2", 10000.0);

        goalRepository.save(user, goal1);
        goalRepository.save(user, goal2);

        List<Goal> goals = goalRepository.getGoalsByEmail(user.getEmail());

        assertEquals(2, goals.size());
        assertEquals("Цель 1", goals.get(0).getName());
        assertEquals("Цель 2", goals.get(1).getName());
    }

    @Test
    void testSaveGoal_withEmptyEmail() {
        User emptyEmailUser = new User("test", "", "password", false);
        Goal goal = new Goal("Цель с пустым email", 1000.0);

        goalRepository.save(emptyEmailUser, goal);

        List<Goal> goals = goalRepository.getGoalsByEmail("");
        assertEquals(1, goals.size());
        assertEquals("Цель с пустым email", goals.get(0).getName());
    }
}