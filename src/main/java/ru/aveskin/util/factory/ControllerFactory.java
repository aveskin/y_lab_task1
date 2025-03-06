package ru.aveskin.util.factory;

import ru.aveskin.buget.controller.BudgetController;
import ru.aveskin.buget.controller.impl.BudgetControllerImpl;
import ru.aveskin.goal.controller.GoalController;
import ru.aveskin.goal.controller.impl.GoalControllerImpl;
import ru.aveskin.notification.controller.NotificationController;
import ru.aveskin.notification.controller.impl.NotificationControllerImpl;
import ru.aveskin.statistic.controller.StatisticsController;
import ru.aveskin.statistic.controller.impl.StatisticsControllerImpl;
import ru.aveskin.transaction.controller.TransactionController;
import ru.aveskin.transaction.controller.impl.TransactionControllerImpl;
import ru.aveskin.user.admin.controller.AdminController;
import ru.aveskin.user.admin.controller.impl.AdminControllerImpl;
import ru.aveskin.user.controller.UserController;
import ru.aveskin.user.controller.impl.UserControllerImpl;

public class ControllerFactory {
    private final ServiceFactory serviceFactory;

    public ControllerFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public UserController createUserController() {
        return new UserControllerImpl(serviceFactory.createUserService());
    }

    public AdminController createAdminController() {
        return new AdminControllerImpl(serviceFactory.createUserService());
    }

    public TransactionController createTransactionController() {
        return new TransactionControllerImpl(serviceFactory.createTransactionService(), serviceFactory.createUserService());
    }

    public BudgetController createBudgetController() {
        return new BudgetControllerImpl(serviceFactory.createBudgetService());
    }

    public GoalController createGoalController() {
        return new GoalControllerImpl(serviceFactory.createGoalService());
    }

    public NotificationController createNotificationController() {
        return new NotificationControllerImpl(serviceFactory.createNotificationService());
    }

    public StatisticsController createStatisticsController() {
        return new StatisticsControllerImpl(serviceFactory.createStatisticsService());
    }
}
