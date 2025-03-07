package ru.aveskin.goal.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.aveskin.goal.model.Goal;
import ru.aveskin.goal.repository.GoalRepository;
import ru.aveskin.notification.service.NotificationService;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GoalServiceImplTest {

    private GoalServiceImpl goalService;
    private GoalRepository goalRepository;
    private User user;

    @BeforeEach
    void setUp() {
        goalRepository = mock(GoalRepository.class);
        NotificationService notificationService = mock(NotificationService.class);

        goalService = new GoalServiceImpl(goalRepository, notificationService);

        user = new User("test", "test@example.com", "password", false);
    }

    @Test
    void testCreateGoal() {
        String goalName = "Новая цель";
        double targetAmount = 1000.0;

        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(() -> ProgramInputHandler.getString("Введите название цели: ")).thenReturn(goalName);
            mocked.when(() -> ProgramInputHandler.getDouble("Введите сумму, которую хотите накопить: ")).thenReturn(targetAmount);

            goalService.createGoal(user);

            ArgumentCaptor<Goal> goalCaptor = ArgumentCaptor.forClass(Goal.class);
            verify(goalRepository, times(1)).save(eq(user), goalCaptor.capture());

            Goal capturedGoal = goalCaptor.getValue();
            assert capturedGoal.getName().equals(goalName);
            assert capturedGoal.getTargetAmount() == targetAmount;
        }
    }

    @Test
    void testShowGoals_whenGoalsExist() {
        Goal goal1 = new Goal("Цель 1", 500.0);
        Goal goal2 = new Goal("Цель 2", 1500.0);

        when(goalRepository.getGoalsByEmail(user.getEmail())).thenReturn(List.of(goal1, goal2));

        goalService.showGoals(user);

        verify(goalRepository, times(1)).getGoalsByEmail(user.getEmail());
    }

    @Test
    void testShowGoals_whenNoGoalsExist() {
        when(goalRepository.getGoalsByEmail(user.getEmail())).thenReturn(new ArrayList<>());

        goalService.showGoals(user);

        verify(goalRepository, times(1)).getGoalsByEmail(user.getEmail());
    }

    @Test
    void testAddSavingsToGoal_whenGoalNotFound() {
        when(goalRepository.getGoalsByEmail(user.getEmail())).thenReturn(new ArrayList<>());

        String goalName = "Не существующая цель";
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(() -> ProgramInputHandler.getString("Введите название цели для изменения:")).thenReturn(goalName);

            goalService.addSavingsToGoal(user);

            verify(goalRepository, times(0)).save(any(User.class), any(Goal.class));
        }
    }
}