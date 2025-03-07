package ru.aveskin.buget.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aveskin.buget.repository.BudgetRepository;
import ru.aveskin.user.model.User;
import ru.aveskin.buget.model.Budget;

import static org.junit.jupiter.api.Assertions.*;

class BudgetRepositoryImplTest {

    private BudgetRepository budgetRepository;
    private User user;

    @BeforeEach
    void setUp() {
        budgetRepository = new BudgetRepositoryImpl();
        user = new User("test", "test@example.com", "password", false);
    }

    @Test
    void testSetLimit() {
        double limit = 1000.0;

        budgetRepository.setLimit(limit, user.getEmail());

        Budget budget = budgetRepository.getUserBudget(user);
        assertNotNull(budget);
        assertEquals(limit, budget.getMonthlyLimit());
    }

    @Test
    void testAddExpense() {
        double limit = 1000.0;
        double expense = 100.0;
        budgetRepository.setLimit(limit, user.getEmail());

        budgetRepository.addExpense(expense, user.getEmail());

        Budget budget = budgetRepository.getUserBudget(user);
        assertEquals(expense, budget.getCurrentExpenses());
    }

    @Test
    void testGetUserBudget() {
        double limit = 1000.0;
        budgetRepository.setLimit(limit, user.getEmail());

        Budget budget = budgetRepository.getUserBudget(user);

        assertNotNull(budget);
        assertEquals(limit, budget.getMonthlyLimit());
    }
}