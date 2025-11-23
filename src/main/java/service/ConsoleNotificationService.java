package service;

import config.Log;

public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyUnavailable(String itemName, String customerName) {
        Log.logger.warning("Dish unavailable: " + itemName);
        System.out.printf("Notification: '%s' is unavailable for customer %s%n", itemName, customerName);
    }
}
