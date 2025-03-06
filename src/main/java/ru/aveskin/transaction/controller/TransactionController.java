package ru.aveskin.transaction.controller;

import ru.aveskin.transaction.model.Transaction;
import ru.aveskin.user.model.User;

import java.util.List;

public interface TransactionController {
    void transactionMenu(User user);

    void showTransactions();
}
