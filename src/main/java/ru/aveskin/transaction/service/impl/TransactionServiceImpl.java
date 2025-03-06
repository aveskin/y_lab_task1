package ru.aveskin.transaction.service.impl;

import ru.aveskin.transaction.model.Transaction;
import ru.aveskin.transaction.model.TransactionType;
import ru.aveskin.transaction.repository.TransactionRepository;
import ru.aveskin.transaction.repository.impl.TransactionRepositoryImpl;
import ru.aveskin.transaction.service.TransactionService;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void add(User user) {
        System.out.println("\nСоздание транзакции:");
        String id = String.valueOf(UUID.randomUUID());
        String email = user.getEmail();
        double amount = ProgramInputHandler.getDouble("Введите сумму: ");
        String typeStr = ProgramInputHandler.getString("Введите тип(Доход = 1, Расход = 2): ");
        TransactionType type = (typeStr.equals("1")) ? TransactionType.INCOME : TransactionType.EXPENSE;

        String category = ProgramInputHandler.getString("Введите категорию(например продукты): ");
        String description = ProgramInputHandler.getString("Введите описание: ");

        Transaction newTransaction = new Transaction(
                id,
                email,
                amount,
                type,
                category,
                description
        );
        transactionRepository.save(newTransaction);
    }

    @Override
    public void showUsersTransactions(User user) {
        List<Transaction> transactions = transactionRepository.getTransactionsList(user.getEmail());
        if (transactions.isEmpty()) {
            System.out.println("У вас пока нет транзакций.");
        } else {
            System.out.println("\nВаши транзакции:");
            System.out.println("-------------------------------------------------");

            transactions.forEach(tr -> {
                System.out.println("ID: " + tr.getId());
                System.out.println("Email: " + tr.getUserEmail());
                System.out.println("Сумма: " + tr.getAmount() + " руб.");
                System.out.println("Тип: " + (tr.getType() == TransactionType.INCOME ? "Доход" : "Расход"));
                System.out.println("Категория: " + tr.getCategory());
                System.out.println("Описание: " + tr.getDescription());
                System.out.println("Дата: " + tr.getDate());

                System.out.println("-------------------------------------------------");
            });
        }
    }

    @Override
    public void deleteTransactionById(User user) {
        System.out.println("\nУдаление транзакции:");
        String id = ProgramInputHandler.getString("Введите id транзакции:");
        boolean isDeleted = transactionRepository.deleteTransactionById(user.getEmail(), id);
        if (isDeleted) {
            System.out.println("Транзакция успешно удалена.");
        } else {
            System.out.println("Транзакция c id =" + id + " не найдена у пользователя.");
        }
    }
}
