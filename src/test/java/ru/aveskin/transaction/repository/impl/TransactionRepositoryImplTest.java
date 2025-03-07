package ru.aveskin.transaction.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aveskin.transaction.model.Transaction;
import ru.aveskin.transaction.model.TransactionType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryImplTest {

    private TransactionRepositoryImpl transactionRepository;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepositoryImpl();
        transaction = new Transaction("1", "test@example.com", 100.0, TransactionType.INCOME, "Продукты", "Покупка еды");
    }

    @Test
    void testSaveTransaction() {
        transactionRepository.save(transaction);

        List<Transaction> transactions = transactionRepository.getTransactionsList("test@example.com");

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }

    @Test
    void testGetTransactionsList_whenNoTransactions() {
        List<Transaction> transactions = transactionRepository.getTransactionsList("test@example.com");

        assertNotNull(transactions);
        assertTrue(transactions.isEmpty());
    }

    @Test
    void testGetTransactionsList_whenTransactionsExist() {
        transactionRepository.save(transaction);

        List<Transaction> transactions = transactionRepository.getTransactionsList("test@example.com");

        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }

    @Test
    void testDeleteTransactionById_whenTransactionExists() {
        transactionRepository.save(transaction);

        boolean result = transactionRepository.deleteTransactionById("test@example.com", transaction.getId());

        assertTrue(result);

        List<Transaction> transactions = transactionRepository.getTransactionsList("test@example.com");
        assertTrue(transactions.isEmpty());
    }

    @Test
    void testDeleteTransactionById_whenTransactionDoesNotExist() {
        transactionRepository.save(transaction);

        boolean result = transactionRepository.deleteTransactionById("test@example.com", "nonexistent-id");

        assertFalse(result);

        List<Transaction> transactions = transactionRepository.getTransactionsList("test@example.com");
        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }

    @Test
    void testDeleteTransactionById_whenUserHasNoTransactions() {
        boolean result = transactionRepository.deleteTransactionById("test@example.com", "any-id");

        assertFalse(result);
    }
}