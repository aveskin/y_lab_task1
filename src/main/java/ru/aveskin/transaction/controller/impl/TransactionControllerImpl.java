package ru.aveskin.transaction.controller.impl;

import ru.aveskin.transaction.controller.TransactionController;
import ru.aveskin.transaction.service.TransactionService;
import ru.aveskin.transaction.service.impl.TransactionServiceImpl;
import ru.aveskin.user.model.User;
import ru.aveskin.user.service.UserService;
import ru.aveskin.user.service.impl.UserServiceImpl;
import ru.aveskin.util.ProgramInputHandler;

public class TransactionControllerImpl implements TransactionController {
    private final TransactionService transactionService = new TransactionServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    public void transactionMenu(User user) {

        while (true) {
            System.out.println("\nУправление транзакциями:");
            System.out.println("1. Добавить транзакцию");
            System.out.println("2. Посмотреть транзакции");
            System.out.println("3. Удалить транзакцию");
            System.out.println("4. Назад");

            int choice = ProgramInputHandler.getChoice();
            switch (choice) {
                case 1 -> transactionService.add(user);
                case 2 -> transactionService.showUsersTransactions(user);
                case 3 -> transactionService.deleteTransactionById(user);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }

    @Override
    public void showTransactions() {
        User user = userService.getUserByEmail();
        if (user != null) {
            transactionService.showUsersTransactions(user);
        }
    }
}
