package ru.aveskin;

import ru.aveskin.user.model.User;
import ru.aveskin.user.util.UserInputHandler;
import ru.aveskin.util.ProgramInputHandler;


public class Main {
    public static void main(String[] args) {
        User currentUser = null;

        while (true) {
            if (currentUser == null) {
                currentUser = showLoginMenu();
            } else {
                currentUser = showUserMenu(currentUser);
            }
        }

    }

    private static User showLoginMenu() {
        System.out.println("\nЛичный финансовый трекер");
        System.out.println("1. Регистрация");
        System.out.println("2. Вход");
        System.out.println("3. Выйти");

        int choice = ProgramInputHandler.getChoice();
        switch (choice) {
            case 1 -> ProgramInputHandler.register();
            case 2 -> {
                User user = ProgramInputHandler.login();
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

    private static User showUserMenu(User currentUser) {
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
            case 1 -> UserInputHandler.viewProfile(currentUser);
            case 2 -> UserInputHandler.updateProfile(currentUser);
            case 3 -> {
                UserInputHandler.deleteProfile(currentUser);
                return null; // Выход в главное меню
            }
            case 4 -> UserInputHandler.transactionMenu(currentUser);
            case 5 -> UserInputHandler.goalMenu(currentUser);
            case 6 -> UserInputHandler.budgetMenu(currentUser);
            case 7 -> UserInputHandler.viewNotifications(currentUser);
            case 8 -> {
                if (currentUser.isAdmin()) UserInputHandler.viewUserList();
                else System.out.println("Недостаточно прав!");
            }
            case 9 -> {
                if (currentUser.isAdmin()) UserInputHandler.viewUserTransactionList();
                else System.out.println("Недостаточно прав!");
            }
            case 10 -> {
                if (currentUser.isAdmin()) UserInputHandler.deleteUser();
                else System.out.println("Недостаточно прав!");
            }
            case 11 -> UserInputHandler.statisticsMenu(currentUser);

            case 99 -> {
                System.out.println("Выход из аккаунта...");
                return null;
            }
            default -> System.out.println("Некорректный ввод, попробуйте снова.");
        }
        return currentUser;
    }
}


