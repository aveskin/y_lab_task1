package ru.aveskin.user.util;

import ru.aveskin.buget.controller.BudgetController;
import ru.aveskin.buget.controller.impl.BudgetControllerImpl;
import ru.aveskin.goal.controller.GoalController;
import ru.aveskin.goal.controller.impl.GoalControllerImpl;
import ru.aveskin.transaction.controller.TransactionController;
import ru.aveskin.transaction.controller.impl.TransactionControllerImpl;
import ru.aveskin.user.admin.controller.AdminController;
import ru.aveskin.user.admin.controller.impl.AdminControllerImpl;
import ru.aveskin.user.controller.UserController;
import ru.aveskin.user.controller.impl.UserControllerImpl;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;


public class UserInputHandler {
    private static final UserController userController = new UserControllerImpl();
    private static final AdminController adminController = new AdminControllerImpl();
    private static final TransactionController transactionController = new TransactionControllerImpl();
    private static final BudgetController budgetController = new BudgetControllerImpl();
    private static final GoalController goalController = new GoalControllerImpl();


    public static void viewProfile(User user) {
        System.out.println("\nДанные профиля:");
        System.out.println("Имя ползователя: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }

    public static void updateProfile(User user) {
        System.out.println("\nИзменение данных профиля:");
        String newName = ProgramInputHandler.getString("Введите новое имя: ");
        user.setName(newName);
        userController.changeUser(user);
    }

    public static void deleteProfile(User user) {
        userController.deleteUser(user);
        System.out.println("Профиль пользователя удален");
    }

    public static void transactionMenu(User user) {
        transactionController.transactionMenu(user);
    }

    public static void goalMenu(User user) {
        goalController.goalMenu(user);
    }

    public static void budgetMenu(User user) {
        budgetController.budgetMenu(user);
    }

    public static void viewUserList() {
        userController.showUserList();
    }

    public static void viewUserTransactionList() {
        transactionController.showTransactions();
    }

    public static void deleteUser() {
        adminController.delete();
    }
}
