package ru.aveskin.goal.controller.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.aveskin.goal.service.GoalService;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

import static org.mockito.Mockito.*;

public class GoalControllerImplTest {

    private GoalService goalService;
    private GoalControllerImpl goalController;
    private User user;

    @BeforeEach
    void setUp() {
        goalService = mock(GoalService.class);
        goalController = new GoalControllerImpl(goalService);
        user = new User("test", "test@example.com", "password", false);
    }

    @Test
    void testGoalMenuOption1() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(1).thenReturn(4);

            goalController.goalMenu(user);

            verify(goalService, times(1)).createGoal(user);
        }
    }

    @Test
    void testGoalMenuOption2() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(2).thenReturn(4);

            goalController.goalMenu(user);

            verify(goalService, times(1)).showGoals(user);
        }
    }

    @Test
    void testGoalMenuOption3() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(3).thenReturn(4);

            goalController.goalMenu(user);

            verify(goalService, times(1)).addSavingsToGoal(user);
        }
    }

    @Test
    void testGoalMenuOption4() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(4);

            goalController.goalMenu(user);
        }
    }
}