package ru.aveskin.user.util;

import ru.aveskin.buget.controller.BudgetController;
import ru.aveskin.goal.controller.GoalController;
import ru.aveskin.notification.controller.NotificationController;
import ru.aveskin.statistic.controller.StatisticsController;
import ru.aveskin.transaction.controller.TransactionController;
import ru.aveskin.user.admin.controller.AdminController;
import ru.aveskin.user.controller.UserController;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;


public class UserInputHandler {
    private final UserController userController;
    private final AdminController adminController;
    private final TransactionController transactionController;
    private final BudgetController budgetController;
    private final GoalController goalController;
    private final NotificationController notificationController;
    private final StatisticsController statisticsController;

    public UserInputHandler(UserController userController,
                            AdminController adminController,
                            TransactionController transactionController,
                            BudgetController budgetController,
                            GoalController goalController,
                            NotificationController notificationController,
                            StatisticsController statisticsController) {
        this.userController = userController;
        this.adminController = adminController;
        this.transactionController = transactionController;
        this.budgetController = budgetController;
        this.goalController = goalController;
        this.notificationController = notificationController;
        this.statisticsController = statisticsController;
    }

    public void viewProfile(User user) {
        System.out.println("\nДанные профиля:");
        System.out.println("Имя ползователя: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }

    public void updateProfile(User user) {
        System.out.println("\nИзменение данных профиля:");
        String newName = ProgramInputHandler.getString("Введите новое имя: ");
        user.setName(newName);
        String newPass = ProgramInputHandler.getString("Введите новый пароль: ");
        user.setPassword(newPass);
        userController.changeUser(user);
    }

    public void deleteProfile(User user) {
        userController.deleteUser(user);
        System.out.println("Профиль пользователя удален");
    }

    public void transactionMenu(User user) {
        transactionController.transactionMenu(user);
    }

    public void goalMenu(User user) {
        goalController.goalMenu(user);
    }

    public void budgetMenu(User user) {
        budgetController.budgetMenu(user);
    }

    public void viewUserList() {
        userController.showUserList();
    }

    public void viewUserTransactionList() {
        transactionController.showTransactions();
    }

    public void deleteUser() {
        adminController.delete();
    }

    public void viewNotifications(User user) {
        notificationController.showNotifications(user);
    }

    public void statisticsMenu(User user) {
        statisticsController.statisticsMenu(user);
    }
}
