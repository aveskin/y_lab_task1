package ru.aveskin.buget.model;

import java.util.Objects;

public class Budget {
    private double monthlyLimit;
    private double currentExpenses;

    public Budget(double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
        this.currentExpenses = 0.0;
    }

    public double getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public double getCurrentExpenses() {
        return currentExpenses;
    }

    public void addExpense(double amount) {
        this.currentExpenses += amount;
    }

    public boolean isOverBudget() {
        return currentExpenses > monthlyLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Double.compare(monthlyLimit, budget.monthlyLimit) == 0 && Double.compare(currentExpenses, budget.currentExpenses) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monthlyLimit, currentExpenses);
    }
}
