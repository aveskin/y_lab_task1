package ru.aveskin.goal.controller.impl;

import ru.aveskin.goal.controller.GoalController;
import ru.aveskin.goal.service.GoalService;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

public class GoalControllerImpl implements GoalController {
    private final GoalService goalService;

    public GoalControllerImpl(GoalService goalService) {
        this.goalService = goalService;
    }

    @Override
    public void goalMenu(User user) {
        while (true) {
            System.out.println("\nФинансовые цели");
            System.out.println("1. Установить новую цель");
            System.out.println("2. Посмотреть цели");
            System.out.println("3. Добавить накопления к цели");
            System.out.println("4. Выйти");

            int choice = ProgramInputHandler.getChoice();
            switch (choice) {
                case 1 -> goalService.createGoal(user);
                case 2 -> goalService.showGoals(user);
                case 3 -> goalService.addSavingsToGoal(user);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }
}
