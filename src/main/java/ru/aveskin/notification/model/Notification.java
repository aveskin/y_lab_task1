package ru.aveskin.notification.model;

import java.util.Objects;

public class Notification {
    private final String message;
    private final String recipientEmail;
    private final boolean isEmailNotification;

    public Notification(String message, String recipientEmail, boolean isEmailNotification) {
        this.message = message;
        this.recipientEmail = recipientEmail;
        this.isEmailNotification = isEmailNotification;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public boolean isEmailNotification() {
        return isEmailNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return isEmailNotification == that.isEmailNotification && Objects.equals(message, that.message) && Objects.equals(recipientEmail, that.recipientEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, recipientEmail, isEmailNotification);
    }
}
