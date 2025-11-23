package service;

import config.Log;

public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyUnavailable(String itemName, String customerName) {
        Log.logger.warning("Блюдо недоступно: " + itemName);
        System.out.printf("Уведомление: '%s' недоступно для клиента %s%n", itemName, customerName);
    }
}
