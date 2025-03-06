package ru.aveskin.notification.service;

import ru.aveskin.user.model.User;

public interface NotificationService {
    void showNotifications(User user);

    void notifyUser(User user, String message, boolean sendEmail);
}
