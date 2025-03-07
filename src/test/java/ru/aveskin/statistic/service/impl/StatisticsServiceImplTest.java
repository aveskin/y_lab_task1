package ru.aveskin.statistic.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.aveskin.transaction.model.Transaction;
import ru.aveskin.transaction.model.TransactionType;
import ru.aveskin.transaction.repository.TransactionRepository;

import java.util.Arrays;
import java.util.Map;

class StatisticsServiceImplTest {

    @Test
    void testGetCurrentBalance() {
        TransactionRepository transactionRepository = mock(TransactionRepository.class);

        Transaction transaction1 = new Transaction("1", "test@example.com", 100,
                TransactionType.INCOME, "Income", "Salary");
        Transaction transaction2 = new Transaction("2", "test@example.com", 50,
                TransactionType.EXPENSE, "Food", "Food");

        when(transactionRepository.getTransactionsList("test@example.com")).thenReturn(Arrays.asList(transaction1, transaction2));

        StatisticsServiceImpl statisticsService = new StatisticsServiceImpl(transactionRepository);

        double balance = statisticsService.getCurrentBalance("test@example.com");
        assertEquals(50.0, balance);
    }

    @Test
    void testGetTotalIncome() {
        TransactionRepository transactionRepository = mock(TransactionRepository.class);

        Transaction transaction1 = new Transaction("1", "test@example.com", 100,
                TransactionType.INCOME, "Income", "Salary");
        Transaction transaction2 = new Transaction("2", "test@example.com", 50,
                TransactionType.EXPENSE, "Food", "Food");

        when(transactionRepository.getTransactionsList("test@example.com")).thenReturn(Arrays.asList(transaction1, transaction2));

        StatisticsServiceImpl statisticsService = new StatisticsServiceImpl(transactionRepository);

        double totalIncome = statisticsService.getTotalIncome("test@example.com");
        assertEquals(100.0, totalIncome);  // Только доходы, должно быть 100
    }

    @Test
    void testGetTotalExpenses() {
        TransactionRepository transactionRepository = mock(TransactionRepository.class);

        Transaction transaction1 = new Transaction("1", "test@example.com", 100,
                TransactionType.INCOME, "Income", "Salary");
        Transaction transaction2 = new Transaction("2", "test@example.com", 50,
                TransactionType.EXPENSE, "Food", "Food");

        when(transactionRepository.getTransactionsList("test@example.com")).thenReturn(Arrays.asList(transaction1, transaction2));

        StatisticsServiceImpl statisticsService = new StatisticsServiceImpl(transactionRepository);

        double totalExpenses = statisticsService.getTotalExpenses("test@example.com");
        assertEquals(50.0, totalExpenses);  // Только расходы, должно быть 50
    }

    @Test
    void testGetExpensesByCategory() {
        TransactionRepository transactionRepository = mock(TransactionRepository.class);

        Transaction transaction1 = new Transaction("1", "test@example.com", 50,
                TransactionType.EXPENSE, "Food", "Lunch");
        Transaction transaction2 = new Transaction("2", "test@example.com", 30,
                TransactionType.EXPENSE, "Transport", "Bus ticket");
        Transaction transaction3 = new Transaction("3", "test@example.com", 20,
                TransactionType.EXPENSE, "Food", "Dinner");

        when(transactionRepository.getTransactionsList("test@example.com")).thenReturn(Arrays.asList(transaction1, transaction2, transaction3));

        StatisticsServiceImpl statisticsService = new StatisticsServiceImpl(transactionRepository);

        Map<String, Double> expenses = statisticsService.getExpensesByCategory("test@example.com");

        assertEquals(70.0, expenses.get("Food"));
        assertEquals(30.0, expenses.get("Transport"));
    }

    @Test
    void testGenerateReport() {

    }
}