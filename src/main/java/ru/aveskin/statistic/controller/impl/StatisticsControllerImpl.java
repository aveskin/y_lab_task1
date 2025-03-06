package ru.aveskin.statistic.controller.impl;

import ru.aveskin.statistic.controller.StatisticsController;
import ru.aveskin.statistic.service.StatisticsService;
import ru.aveskin.statistic.service.impl.StatisticsServiceImpl;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

import java.util.Map;

public class StatisticsControllerImpl implements StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsControllerImpl(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Override
    public void statisticsMenu(User user) {
        while (true) {
            System.out.println("\nСтатистика и аналитика:");
            System.out.println("1. Подсчёт текущего баланса");
            System.out.println("2. Расчёт суммарного дохода");
            System.out.println("3. Расчёт суммарных расходов");
            System.out.println("4. Анализ расходов по категориям");
            System.out.println("5. Формирование отчёта");
            System.out.println("6. Выйти");

            int choice = ProgramInputHandler.getChoice();
            switch (choice) {
                case 1 -> System.out.println("Текущий баланс: " + statisticsService.getCurrentBalance(user.getEmail()));
                case 2 -> System.out.println("Суммарный доход: " + statisticsService.getTotalIncome(user.getEmail()));
                case 3 ->
                        System.out.println("Суммарные расходы: " + statisticsService.getTotalExpenses(user.getEmail()));
                case 4 -> {
                    Map<String, Double> expensesByCategory = statisticsService.getExpensesByCategory(user.getEmail());
                    expensesByCategory.forEach((category, amount) ->
                            System.out.println(category + ": " + amount)
                    );
                }
                case 5 -> statisticsService.generateReport(user.getEmail());
                case 6 -> {
                    return;
                }
                default -> System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }
}
