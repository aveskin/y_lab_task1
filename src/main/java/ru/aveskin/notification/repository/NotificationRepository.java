package ru.aveskin.notification.repository;

import ru.aveskin.notification.model.Notification;
import ru.aveskin.user.model.User;

import java.util.List;

public interface NotificationRepository {
    void addNotification(Notification notification, User user);

    List<Notification> getNotificationsForUser(User user);
}
