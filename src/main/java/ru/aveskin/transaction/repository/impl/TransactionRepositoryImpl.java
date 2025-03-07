package ru.aveskin.transaction.repository.impl;

import ru.aveskin.transaction.model.Transaction;
import ru.aveskin.transaction.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepositoryImpl implements TransactionRepository {
    private final Map<String, List<Transaction>> usersTransactions = new HashMap<>();

    @Override
    public void save(Transaction transaction) {
        usersTransactions
                .computeIfAbsent(transaction.getUserEmail(), k -> new ArrayList<>())
                .add(transaction);
    }

    @Override
    public List<Transaction> getTransactionsList(String email) {
        return usersTransactions.getOrDefault(email, new ArrayList<>());
    }

    @Override
    public boolean deleteTransactionById(String email, String id) {
        List<Transaction> userTransactions = usersTransactions.get(email);

        if (userTransactions == null || userTransactions.isEmpty()) {
            return false;
        }

        return userTransactions.removeIf(tr -> tr.getId().equals(id));
    }
}
