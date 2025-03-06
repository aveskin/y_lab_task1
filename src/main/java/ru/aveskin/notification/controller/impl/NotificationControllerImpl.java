package ru.aveskin.notification.controller.impl;

import ru.aveskin.notification.controller.NotificationController;
import ru.aveskin.notification.service.NotificationService;
import ru.aveskin.notification.service.impl.NotificationServiceImpl;
import ru.aveskin.user.model.User;

public class NotificationControllerImpl implements NotificationController {
    private final NotificationService notificationService = new NotificationServiceImpl();

    @Override
    public void showNotifications(User user) {
        notificationService.showNotifications(user);
    }
}
