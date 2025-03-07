package ru.aveskin.buget.controller.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.aveskin.buget.service.BudgetService;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

import static org.mockito.Mockito.*;

class BudgetControllerImplTest {

    private BudgetService budgetService;
    private BudgetControllerImpl budgetController;
    private User user;

    @BeforeEach
    void setUp() {
        budgetService = mock(BudgetService.class);
        budgetController = new BudgetControllerImpl(budgetService);
        user = new User("test", "test@example.com", "password", false);
    }

    @Test
    void testBudgetMenuOption1() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(1).thenReturn(4);

            budgetController.budgetMenu(user);

            verify(budgetService, times(1)).setBudget(user);
        }
    }

    @Test
    void testBudgetMenuOption2() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(2).thenReturn(4);

            budgetController.budgetMenu(user);

            verify(budgetService, times(1)).showCurrentBudget(user);
        }
    }

    @Test
    void testBudgetMenuOption3() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(3).thenReturn(4);

            budgetController.budgetMenu(user);

            verify(budgetService, times(1)).addExpense(user);
        }
    }

    @Test
    void testBudgetMenuOption4() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(4);

            budgetController.budgetMenu(user);
        }
    }
}