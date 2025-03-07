package ru.aveskin.transaction.controller.impl;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import ru.aveskin.transaction.service.TransactionService;
import ru.aveskin.user.model.User;
import ru.aveskin.user.service.UserService;
import ru.aveskin.util.ProgramInputHandler;

import static org.mockito.Mockito.*;


class TransactionControllerImplTest {

    @Test
    void testTransactionMenu_addTransaction() {
        TransactionService transactionService = mock(TransactionService.class);
        UserService userService = mock(UserService.class);
        User user = new User("test", "test@example.com", "password", false);

        try (MockedStatic<ProgramInputHandler> mocked = mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(1).thenReturn(4);

            TransactionControllerImpl transactionController = new TransactionControllerImpl(transactionService, userService);

            transactionController.transactionMenu(user);

            verify(transactionService, times(1)).add(user);
        }
    }

    @Test
    void testTransactionMenu_showTransactions() {
        TransactionService transactionService = mock(TransactionService.class);
        UserService userService = mock(UserService.class);
        User user = new User("test", "test@example.com", "password", false);

        try (MockedStatic<ProgramInputHandler> mocked = mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(2).thenReturn(4);

            TransactionControllerImpl transactionController = new TransactionControllerImpl(transactionService, userService);

            transactionController.transactionMenu(user);

            verify(transactionService, times(1)).showUsersTransactions(user);
        }
    }

    @Test
    void testTransactionMenu_deleteTransaction() {
        // Мокируем зависимости
        TransactionService transactionService = mock(TransactionService.class);
        UserService userService = mock(UserService.class);
        User user = new User("test", "test@example.com", "password", false);

        try (MockedStatic<ProgramInputHandler> mocked = mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(3).thenReturn(4); // Выбор 3 (Удалить транзакцию), затем 4 (Назад)

            mocked.when(() -> ProgramInputHandler.getString(anyString())).thenReturn("12345"); // Вводим id транзакции "12345"

            TransactionControllerImpl transactionController = new TransactionControllerImpl(transactionService, userService);

            transactionController.transactionMenu(user);

            verify(transactionService, times(1)).deleteTransactionById(user);
        }
    }

    @Test
    void testTransactionMenu_exit() {
        TransactionService transactionService = mock(TransactionService.class);
        UserService userService = mock(UserService.class);
        User user = new User("test", "test@example.com", "password", false);

        try (MockedStatic<ProgramInputHandler> mocked = mockStatic(ProgramInputHandler.class)) {
            mocked.when(ProgramInputHandler::getChoice).thenReturn(4); // Выбор 4 (Назад)

            TransactionControllerImpl transactionController = new TransactionControllerImpl(transactionService, userService);

            transactionController.transactionMenu(user);

            verify(transactionService, times(0)).add(user);
            verify(transactionService, times(0)).showUsersTransactions(user);
            verify(transactionService, times(0)).deleteTransactionById(user);
        }
    }

    @Test
    void testShowTransactions() {
        TransactionService transactionService = mock(TransactionService.class);
        UserService userService = mock(UserService.class);
        User user = new User("test", "test@example.com", "password", false);

        when(userService.getUserByEmail()).thenReturn(user);

        TransactionControllerImpl transactionController = new TransactionControllerImpl(transactionService, userService);

        transactionController.showTransactions();

        verify(transactionService, times(1)).showUsersTransactions(user);
    }

    @Test
    void testShowTransactions_userNotFound() {
        TransactionService transactionService = mock(TransactionService.class);
        UserService userService = mock(UserService.class);

        when(userService.getUserByEmail()).thenReturn(null);

        TransactionControllerImpl transactionController = new TransactionControllerImpl(transactionService, userService);

        transactionController.showTransactions();

        verify(transactionService, times(0)).showUsersTransactions(any());
    }
}