package ru.aveskin.statistic.service;

import java.util.Map;

public interface StatisticsService {
    double getCurrentBalance(String email);
    double getTotalIncome(String email);
    double getTotalExpenses(String email);
    Map<String, Double> getExpensesByCategory(String email);
    void generateReport(String email);
}
