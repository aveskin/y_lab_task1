package ru.aveskin.util.factory;

import ru.aveskin.buget.repository.BudgetRepository;
import ru.aveskin.buget.repository.impl.BudgetRepositoryImpl;
import ru.aveskin.goal.repository.GoalRepository;
import ru.aveskin.goal.repository.impl.GoalRepositoryImpl;
import ru.aveskin.notification.repository.NotificationRepository;
import ru.aveskin.notification.repository.impl.NotificationRepositoryImpl;
import ru.aveskin.transaction.repository.TransactionRepository;
import ru.aveskin.transaction.repository.impl.TransactionRepositoryImpl;
import ru.aveskin.user.repository.UserRepository;
import ru.aveskin.user.repository.impl.UserRepositoryImpl;

public class RepositoryFactory {
    public UserRepository createUserRepository() {
        return new UserRepositoryImpl();
    }

    public TransactionRepository createTransactionRepository() {
        return new TransactionRepositoryImpl();
    }

    public BudgetRepository createBudgetRepository() {
        return new BudgetRepositoryImpl();
    }

    public GoalRepository createGoalRepository() {
        return new GoalRepositoryImpl();
    }

    public NotificationRepository createNotificationRepository() {
        return new NotificationRepositoryImpl();
    }

    public TransactionRepository createStatisticsRepository() {
        return new TransactionRepositoryImpl();
    }
}
