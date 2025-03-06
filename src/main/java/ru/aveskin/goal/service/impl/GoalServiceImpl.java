package ru.aveskin.goal.service.impl;

import ru.aveskin.goal.model.Goal;
import ru.aveskin.goal.repository.GoalRepository;
import ru.aveskin.goal.repository.impl.GoalRepositoryImpl;
import ru.aveskin.goal.service.GoalService;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

import java.util.List;
import java.util.Optional;

public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository = new GoalRepositoryImpl();

    @Override
    public void createGoal(User user) {
        System.out.println("\nУстановка цели:");
        String goalName = ProgramInputHandler.getString("Введите название цели: ");
        double targetAmount = ProgramInputHandler.getDouble("Введите сумму, которую хотите накопить: ");
        Goal newGoal = new Goal(goalName, targetAmount);

        goalRepository.save(user, newGoal);
    }

    @Override
    public void showGoals(User user) {
        List<Goal> goals = goalRepository.getGoalsByEmail(user.getEmail());
        if (goals == null || goals.isEmpty()) {
            System.out.println("У вас ещё нет установленных целей.");
            return;
        }

        System.out.println("\nВаши финансовые цели:");
        System.out.println("-------------------------------------------------");

        goals.forEach(g -> {
            System.out.println("Цель: " + g.getName());
            System.out.println("Текущие накопления: " + g.getCurrentAmount());
            System.out.println("Прогресс: " + g.getProgressPercentage());
        });


    }

    @Override
    public void addSavingsToGoal(User user) {
        String goalName = ProgramInputHandler.getString("Введите название цели для изменения: ");
        List<Goal> goals = goalRepository.getGoalsByEmail(user.getEmail());

        if (goals == null || goals.isEmpty()) {
            System.out.println("Неверный выбор цели.");
            return;
        }

        Optional<Goal> goalOp = goals.stream().filter(g -> g.getName().equals(goalName)).findFirst();
        if (goalOp.isPresent()) {
            double amount = ProgramInputHandler.getDouble("Введите сумму, которую хотите добавить: ");
            Goal goal = goalOp.get();
            goal.addSavings(amount);
            goalRepository.save(user, goal);
            System.out.println("Добавлено " + amount + " к цели '" + goal.getName() + "'");
        } else {
            System.out.println("Неверный выбор цели.");
            return;
        }
    }
}
