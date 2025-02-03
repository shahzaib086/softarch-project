package com.example.notification.service;

public class EmailNotificationService implements NotificationService {

    private static EmailNotificationService instance;

    private EmailNotificationService() { }

    public static synchronized EmailNotificationService getInstance() {
        if (instance == null) {
            instance = new EmailNotificationService();
        }
        return instance;
    }

    /**
     * Notifies by sending an email.
     *
     * @param receiverId   the recipient's email address
     * @param title   the title of the notification
     * @param message the message content of the notification
     * @return true if the notification was sent successfully
     * @throws Exception if an error occurs during notification
     */
    @Override
    public Boolean notify(Long receiverId, String title, String message) {
        try {
            System.out.println("Notify:: Receiver Id : " + receiverId);
            System.out.println("Notify:: Title: " + title);
            System.out.println("Notify:: Message: " + message);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
