package ru.aveskin.buget.service.impl;

import ru.aveskin.buget.model.Budget;
import ru.aveskin.buget.repository.BudgetRepository;
import ru.aveskin.buget.repository.impl.BudgetRepositoryImpl;
import ru.aveskin.buget.service.BudgetService;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository = new BudgetRepositoryImpl();

    @Override
    public void setBudget(User user) {
        System.out.println("\nУстановка месячного бюджета:");
        double limit = ProgramInputHandler.getDouble("Введите сумму бюджета: ");
        budgetRepository.setLimit(limit, user.getEmail());
        System.out.println("Бюджет установлен " + limit);
    }

    @Override
    public void showCurrentBudget(User user) {
        Budget budget = budgetRepository.getUserBudget(user);
        if (budget == null) {
            System.out.println("У вас ещё нет установленного бюджета.");
        } else {
            System.out.println("Ваш бюджет: " + budget.getMonthlyLimit());
            System.out.println("Потрачено в этом месяце: " + budget.getCurrentExpenses());
            if (budget.isOverBudget()) {
                System.out.println("ВНИМАНИЕ: Вы превысили бюджет!");
            }
        }
    }

    @Override
    public void addExpense(User user) {
        System.out.println("\nДобавление тарты:");
        double expense = ProgramInputHandler.getDouble("Введите сумму траты: ");
        budgetRepository.addExpense(expense, user.getEmail());
        System.out.println("Трата добвлена");
    }
}
