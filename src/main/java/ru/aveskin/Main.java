package ru.aveskin;

import ru.aveskin.buget.controller.BudgetController;
import ru.aveskin.goal.controller.GoalController;
import ru.aveskin.notification.controller.NotificationController;
import ru.aveskin.statistic.controller.StatisticsController;
import ru.aveskin.transaction.controller.TransactionController;
import ru.aveskin.user.admin.controller.AdminController;
import ru.aveskin.user.controller.UserController;
import ru.aveskin.user.model.User;
import ru.aveskin.user.util.UserInputHandler;
import ru.aveskin.util.ProgramInputHandler;
import ru.aveskin.util.factory.ControllerFactory;
import ru.aveskin.util.factory.RepositoryFactory;
import ru.aveskin.util.factory.ServiceFactory;


public class Main {
    public static void main(String[] args) {

        RepositoryFactory repositoryFactory = new RepositoryFactory();
        ServiceFactory serviceFactory = new ServiceFactory(repositoryFactory);
        ControllerFactory controllerFactory = new ControllerFactory(serviceFactory);

        UserController userController = controllerFactory.createUserController();
        AdminController adminController = controllerFactory.createAdminController();
        TransactionController transactionController = controllerFactory.createTransactionController();
        BudgetController budgetController = controllerFactory.createBudgetController();
        GoalController goalController = controllerFactory.createGoalController();
        NotificationController notificationController = controllerFactory.createNotificationController();
        StatisticsController statisticsController = controllerFactory.createStatisticsController();


        UserInputHandler userInputHandler = new UserInputHandler(
                userController,
                adminController,
                transactionController,
                budgetController,
                goalController,
                notificationController,
                statisticsController
        );

        ProgramInputHandler programInputHandler = new ProgramInputHandler(userController);


        User currentUser = null;

        while (true) {
            if (currentUser == null) {
                currentUser = showLoginMenu(programInputHandler);
            } else {
                currentUser = showUserMenu(currentUser, userInputHandler);
            }
        }

    }

    private static User showLoginMenu(ProgramInputHandler programInputHandler) {
        System.out.println("\nЛичный финансовый трекер");
        System.out.println("1. Регистрация");
        System.out.println("2. Вход");
        System.out.println("3. Выйти");

        int choice = ProgramInputHandler.getChoice();
        switch (choice) {
            case 1 -> programInputHandler.register();
            case 2 -> {
                User user = programInputHandler.login();
                if (user != null) {
                    return user;
                }
            }
            case 3 -> {
                System.out.println("Выход из программы...");
                System.exit(0);
            }
            default -> System.out.println("Некорректный ввод, попробуйте снова.");
        }
        return null;
    }

    private static User showUserMenu(User currentUser, UserInputHandler userInputHandler) {
        System.out.println("\nДобро пожаловать, " + currentUser.getName());
        System.out.println("1. Просмотреть профиль");
        System.out.println("2. Изменить профиль");
        System.out.println("3. Удалить профиль");
        System.out.println("4. Управление транзакциями");
        System.out.println("5. Управление целями");
        System.out.println("6. Управление бюджетом");
        System.out.println("7. Посмотреть уведомления");
        System.out.println("8. Просмотр списка пользователей");
        System.out.println("9. Просмотр списка транзакций у пользователя");
        System.out.println("10. Удалить пользователя");
        System.out.println("11. Статистика и аналитика");
        System.out.println("99. Выйти из аккаунта");

        int choice = ProgramInputHandler.getChoice();


        switch (choice) {
            case 1 -> userInputHandler.viewProfile(currentUser);
            case 2 -> userInputHandler.updateProfile(currentUser);
            case 3 -> {
                userInputHandler.deleteProfile(currentUser);
                return null; // Выход в главное меню
            }
            case 4 -> userInputHandler.transactionMenu(currentUser);
            case 5 -> userInputHandler.goalMenu(currentUser);
            case 6 -> userInputHandler.budgetMenu(currentUser);
            case 7 -> userInputHandler.viewNotifications(currentUser);
            case 8 -> {
                if (currentUser.isAdmin()) userInputHandler.viewUserList();
                else System.out.println("Недостаточно прав!");
            }
            case 9 -> {
                if (currentUser.isAdmin()) userInputHandler.viewUserTransactionList();
                else System.out.println("Недостаточно прав!");
            }
            case 10 -> {
                if (currentUser.isAdmin()) userInputHandler.deleteUser();
                else System.out.println("Недостаточно прав!");
            }
            case 11 -> userInputHandler.statisticsMenu(currentUser);

            case 99 -> {
                System.out.println("Выход из аккаунта...");
                return null;
            }
            default -> System.out.println("Некорректный ввод, попробуйте снова.");
        }
        return currentUser;
    }
}


