package ru.aveskin.transaction.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ru.aveskin.transaction.model.Transaction;
import ru.aveskin.transaction.model.TransactionType;
import ru.aveskin.transaction.repository.TransactionRepository;
import ru.aveskin.user.model.User;
import ru.aveskin.util.ProgramInputHandler;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("test", "test@example.com", "password", false);
    }

    @Test
    void testAddTransaction() {
        double expectedAmount = 1000.0;
        String expectedType = "1";
        String expectedCategory = "Продукты";
        String expectedDescription = "Покупка еды";

        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(() -> ProgramInputHandler.getDouble(anyString())).thenReturn(expectedAmount);
            mocked.when(() -> ProgramInputHandler.getString(anyString()))
                    .thenReturn(expectedType)
                    .thenReturn(expectedCategory)
                    .thenReturn(expectedDescription);

            transactionService.add(user);

            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }
    }

    @Test
    void testShowUsersTransactions_whenNoTransactions() {
        when(transactionRepository.getTransactionsList(user.getEmail())).thenReturn(Collections.emptyList());

        transactionService.showUsersTransactions(user);

        verify(transactionRepository, times(1)).getTransactionsList(user.getEmail());
    }

    @Test
    void testShowUsersTransactions_whenTransactionsExist() {
        Transaction transaction = new Transaction(
                "1",
                user.getEmail(),
                5000.0,
                TransactionType.INCOME,
                "Продукты",
                "Покупка еды"
        );
        when(transactionRepository.getTransactionsList(user.getEmail())).thenReturn(List.of(transaction));

        transactionService.showUsersTransactions(user);

        verify(transactionRepository, times(1)).getTransactionsList(user.getEmail());
    }

    @Test
    void testDeleteTransactionById_whenTransactionExists() {
        // Мокируем метод ProgramInputHandler.getString для эмуляции ввода id транзакции
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(() -> ProgramInputHandler.getString("Введите id транзакции:"))
                    .thenReturn("1");  // Эмулируем ввод id транзакции "1"

            when(transactionRepository.deleteTransactionById(user.getEmail(), "1")).thenReturn(true);

            transactionService.deleteTransactionById(user);

            verify(transactionRepository, times(1)).deleteTransactionById(user.getEmail(), "1");
        }
    }

    @Test
    void testDeleteTransactionById_whenTransactionNotFound() {
        try (MockedStatic<ProgramInputHandler> mocked = Mockito.mockStatic(ProgramInputHandler.class)) {
            mocked.when(() -> ProgramInputHandler.getString("Введите id транзакции:"))
                    .thenReturn("1");  // Эмулируем ввод id транзакции "1"

            when(transactionRepository.deleteTransactionById(user.getEmail(), "1")).thenReturn(false);

            transactionService.deleteTransactionById(user);

            verify(transactionRepository, times(1)).deleteTransactionById(user.getEmail(), "1");
        }
    }
}