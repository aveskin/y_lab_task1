package ru.aveskin.statistic.service.impl;

import ru.aveskin.statistic.service.StatisticsService;
import ru.aveskin.transaction.model.Transaction;
import ru.aveskin.transaction.model.TransactionType;
import ru.aveskin.transaction.repository.TransactionRepository;
import ru.aveskin.transaction.repository.impl.TransactionRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsServiceImpl implements StatisticsService {
    private final TransactionRepository transactionRepository;

    public StatisticsServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public double getCurrentBalance(String email) {
        List<Transaction> transactions = transactionRepository.getTransactionsList(email);
        return transactions.stream()
                .mapToDouble(tr -> tr.getType() == TransactionType.INCOME ? tr.getAmount() : -tr.getAmount())
                .sum();
    }

    @Override
    public double getTotalIncome(String email) {
        return transactionRepository.getTransactionsList(email).stream()
                .filter(tr -> tr.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getTotalExpenses(String email) {
        return transactionRepository.getTransactionsList(email).stream()
                .filter(tr -> tr.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public Map<String, Double> getExpensesByCategory(String email) {
        return transactionRepository.getTransactionsList(email).stream()
                .filter(tr -> tr.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)));
    }

    @Override
    public void generateReport(String email) {
        System.out.println("\nФинансовый отчет для: " + email);
        System.out.println("Текущий баланс: " + getCurrentBalance(email));
        System.out.println("Общий доход: " + getTotalIncome(email));
        System.out.println("Общие расходы: " + getTotalExpenses(email));

        Map<String, Double> expensesByCategory = getExpensesByCategory(email);
        System.out.println("Расходы по категориям:");
        expensesByCategory.forEach((category, amount) ->
                System.out.println(" - " + category + ": " + amount)
        );
    }
}
