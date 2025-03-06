package ru.aveskin.transaction.repository;

import ru.aveskin.transaction.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);

    List<Transaction> getTransactionsList(String email);

    boolean deleteTransactionById(String email, String id);
}
