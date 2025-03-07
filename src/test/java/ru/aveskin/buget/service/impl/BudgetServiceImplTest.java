package ru.aveskin.buget.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.aveskin.buget.model.Budget;
import ru.aveskin.buget.repository.BudgetRepository;
import ru.aveskin.notification.service.NotificationService;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

import static org.mockito.Mockito.*;

class BudgetServiceImplTest {

    private BudgetRepository budgetRepository;
    private BudgetServiceImpl budgetService;
    private User user;

    @BeforeEach
    void setUp() {
        budgetRepository = mock(BudgetRepository.class);
        budgetService = new BudgetServiceImpl(budgetRepository, mock(NotificationService.class));
        user = new User("test", "test@example.com", "password", false);
    }

    @Test
    void testSetBudget() {

        double expectedLimit = 1000.0;

        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(() -> ProgramInputHandler.getDouble(anyString())).thenReturn(expectedLimit);

            budgetService.setBudget(user);

            verify(budgetRepository, times(1)).setLimit(expectedLimit, user.getEmail());
        }
    }

    @Test
    void testShowCurrentBudgetWhenNoBudgetSet() {
        when(budgetRepository.getUserBudget(user)).thenReturn(null);

        budgetService.showCurrentBudget(user);

        verify(budgetRepository, times(1)).getUserBudget(user);
    }

    @Test
    void testShowCurrentBudgetWhenBudgetSet() {
        Budget budget = new Budget(1000.0);
        when(budgetRepository.getUserBudget(user)).thenReturn(budget);

        budgetService.showCurrentBudget(user);

        verify(budgetRepository, times(1)).getUserBudget(user);
    }

    @Test
    void testAddExpense() {
        double expectedExpense = 100.0;

        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(() -> ProgramInputHandler.getDouble(anyString())).thenReturn(expectedExpense);

            doNothing().when(budgetRepository).addExpense(expectedExpense, user.getEmail());

            budgetService.addExpense(user);

            verify(budgetRepository, times(1)).addExpense(expectedExpense, user.getEmail());
        }
    }
}