package ru.aveskin.goal.model;

import java.util.Objects;

public class Goal {
    private String name;
    private double targetAmount;
    private double currentAmount;

    public Goal(String name, double targetAmount) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void addSavings(double amount) {
        this.currentAmount += amount;
    }

    public boolean isGoalReached() {
        return currentAmount >= targetAmount;
    }

    public double getProgressPercentage() {
        return (currentAmount / targetAmount) * 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Double.compare(targetAmount, goal.targetAmount) == 0 && Double.compare(currentAmount, goal.currentAmount) == 0 && Objects.equals(name, goal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, targetAmount, currentAmount);
    }
}
