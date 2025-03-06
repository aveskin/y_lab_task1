package ru.aveskin.buget.repository.impl;

import ru.aveskin.buget.model.Budget;
import ru.aveskin.buget.repository.BudgetRepository;
import ru.aveskin.user.model.User;

import java.util.HashMap;
import java.util.Map;

public class BudgetRepositoryImpl implements BudgetRepository {
    private final Map<String, Budget> userBudget = new HashMap<>();


    @Override
    public void setLimit(double limit, String email) {
        userBudget.put(email, new Budget(limit));
    }

    @Override
    public void addExpense(double expense, String email) {
        userBudget.get(email).addExpense(expense);
    }

    @Override
    public Budget getUserBudget(User user) {
        return userBudget.get(user.getEmail());
    }
}
