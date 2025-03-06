package ru.aveskin.buget.repository;

import ru.aveskin.buget.model.Budget;
import ru.aveskin.user.model.User;

public interface BudgetRepository {
    void setLimit(double limit, String email);

    void addExpense(double expense, String email);

    Budget getUserBudget(User user);
}
