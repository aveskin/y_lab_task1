package ru.aveskin.util.factory;

import ru.aveskin.buget.service.BudgetService;
import ru.aveskin.buget.service.impl.BudgetServiceImpl;
import ru.aveskin.goal.service.GoalService;
import ru.aveskin.goal.service.impl.GoalServiceImpl;
import ru.aveskin.notification.service.NotificationService;
import ru.aveskin.notification.service.impl.NotificationServiceImpl;
import ru.aveskin.statistic.service.StatisticsService;
import ru.aveskin.statistic.service.impl.StatisticsServiceImpl;
import ru.aveskin.transaction.service.TransactionService;
import ru.aveskin.transaction.service.impl.TransactionServiceImpl;
import ru.aveskin.user.service.UserService;
import ru.aveskin.user.service.impl.UserServiceImpl;

public class ServiceFactory {
    private final RepositoryFactory repositoryFactory;

    public ServiceFactory(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    public UserService createUserService() {
        return new UserServiceImpl(repositoryFactory.createUserRepository());
    }

    public TransactionService createTransactionService() {
        return new TransactionServiceImpl(repositoryFactory.createTransactionRepository());
    }

    public BudgetService createBudgetService() {
        return new BudgetServiceImpl(repositoryFactory.createBudgetRepository(), createNotificationService());
    }

    public GoalService createGoalService() {
        return new GoalServiceImpl(repositoryFactory.createGoalRepository(), createNotificationService());
    }

    public NotificationService createNotificationService() {
        return new NotificationServiceImpl(repositoryFactory.createNotificationRepository());
    }

    public StatisticsService createStatisticsService(){
        return new StatisticsServiceImpl(repositoryFactory.createStatisticsRepository());
    }
}
