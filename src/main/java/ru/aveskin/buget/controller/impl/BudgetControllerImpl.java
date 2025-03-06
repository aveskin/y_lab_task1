package ru.aveskin.buget.controller.impl;

import ru.aveskin.buget.controller.BudgetController;
import ru.aveskin.buget.service.BudgetService;
import ru.aveskin.buget.service.impl.BudgetServiceImpl;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

public class BudgetControllerImpl implements BudgetController {
    private final BudgetService budgetService = new BudgetServiceImpl();

    @Override
    public void budgetMenu(User user) {
        while (true) {
            System.out.println("\nУправление бюджетом:");
            System.out.println("1. Установить бюджет");
            System.out.println("2. Показать текущий бюджет");
            System.out.println("3. Добавить расход");
            System.out.println("4. Назад");

            int choice = ProgramInputHandler.getChoice();
            switch (choice) {
                case 1 -> budgetService.setBudget(user);
                case 2 -> budgetService.showCurrentBudget(user);
                case 3 -> budgetService.addExpense(user);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }
}

