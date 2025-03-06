package ru.aveskin.notification.repository.impl;

import ru.aveskin.notification.model.Notification;
import ru.aveskin.notification.repository.NotificationRepository;
import ru.aveskin.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationRepositoryImpl implements NotificationRepository {
    private final Map<String, List<Notification>> notifications = new HashMap<>();


    @Override
    public void addNotification(Notification notification, User user) {
        notifications
                .computeIfAbsent(user.getEmail(), k -> new ArrayList<>())
                .add(notification);
    }

    @Override
    public List<Notification> getNotificationsForUser(User user) {
        return notifications.getOrDefault(user.getEmail(), new ArrayList<>());
    }
}
