package ru.aveskin.notification.service.impl;

import ru.aveskin.notification.model.Notification;
import ru.aveskin.notification.repository.NotificationRepository;
import ru.aveskin.notification.repository.impl.NotificationRepositoryImpl;
import ru.aveskin.notification.service.NotificationService;
import ru.aveskin.user.model.User;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void showNotifications(User user) {
        List<Notification> notifications = notificationRepository.getNotificationsForUser(user);
        if (!notifications.isEmpty()) {
            notifications.forEach(notification -> System.out.println(notification.getMessage()));
        } else {
            System.out.println("нет оповещенияй для пользователя" + user.getName());
        }
    }

    @Override
    public void notifyUser(User user, String message, boolean sendEmail) {
        Notification notification = new Notification(message, user.getEmail(), sendEmail);
        notificationRepository.addNotification(notification, user);

        if (sendEmail) {
            sendEmailNotification(user.getEmail(), message);
        }
    }

    private void sendEmailNotification(String email, String message) {
        // Имитация отправки на Email
        //sendEmail()
        System.out.println("📧 Email отправлен на " + email + ": " + message);
    }
}
