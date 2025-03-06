package ru.aveskin.buget.service;

import ru.aveskin.user.model.User;

public interface BudgetService {
    void setBudget(User user);

    void showCurrentBudget(User user);

    void addExpense(User user);
}
