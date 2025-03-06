package ru.aveskin;

import ru.aveskin.user.model.User;
import ru.aveskin.user.util.UserInputHandler;
import ru.aveskin.util.ProgramInputHandler;


public class Main {
    public static void main(String[] args) {
        User currentUser = null;

        while (true) {
            if (currentUser == null) {
                System.out.println("\nЛичный финансовый трекер");
                System.out.println("1. Регистрация");
                System.out.println("2. Вход");
                System.out.println("3. Выйти");

                int choice = ProgramInputHandler.getChoice();

                switch (choice) {
                    case 1 -> ProgramInputHandler.register();
                    case 2 -> currentUser = ProgramInputHandler.login();
                    case 3 -> {
                        System.out.println("Выход из программы...");
                        return;
                    }
                    default -> System.out.println("Некорректный ввод, попробуйте снова.");
                }
            } else {
                System.out.println("\nДобро пожаловать, " + currentUser.getName());
                System.out.println("1. Просмотреть профиль");
                System.out.println("2. Изменить профиль");
                System.out.println("3. Удалить профиль");
                System.out.println("4. Управление транзакциями");
                System.out.println("5. Управление целями");
                System.out.println("6. Управление бюджетом");
                System.out.println("7. Выйти из аккаунта");

                if (currentUser.isAdmin()) {
                    System.out.println("8. Просмотр списка пользователей");
                    System.out.println("9. Просмотр списка транзакций у пользователя");
                    System.out.println("10. Удалить пользователя");
                }

                int choice = ProgramInputHandler.getChoice();

                switch (choice) {
                    case 1 -> UserInputHandler.viewProfile(currentUser);
                    case 2 -> UserInputHandler.updateProfile(currentUser);
                    case 3 -> {
                        UserInputHandler.deleteProfile(currentUser);
                        currentUser = null;
                    }
                    case 4 -> UserInputHandler.transactionMenu(currentUser);
                    case 5 -> UserInputHandler.goalMenu(currentUser);
                    case 6 -> UserInputHandler.budgetMenu(currentUser);
                    case 7 -> {
                        System.out.println("Выход из аккаунта...");
                        currentUser = null;
                    }
                    case 8 -> UserInputHandler.viewUserList();
                    case 9 -> UserInputHandler.viewUserTransactionList();
                    case 10 -> UserInputHandler.deleteUser();
                    default -> System.out.println("Некорректный ввод, попробуйте снова.");
                }
            }
        }
    }
}