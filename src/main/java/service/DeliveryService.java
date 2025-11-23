package service;

import config.Log;
import model.Order;

public class DeliveryService {
    public void deliver(Order order, String courierName) {
        Log.logger.info("Доставка начата курьером " + courierName);
        System.out.printf("Курьер %s доставил заказ #%d%n", courierName, order.hashCode());
        order.markDelivered();
    }
}
