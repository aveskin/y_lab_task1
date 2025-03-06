package ru.aveskin.transaction.service;

import ru.aveskin.user.model.User;

public interface TransactionService {
    void add(User user);

    void showUsersTransactions(User user);

    void deleteTransactionById(User user);
}
