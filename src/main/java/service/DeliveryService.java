package service;

import config.Log;
import model.Order;

public class DeliveryService {
    public void deliver(Order order, String courierName) {
        Log.logger.info("Delivery started by courier " + courierName);
        System.out.printf("Courier %s delivered order #%d%n", courierName, order.hashCode());
        order.markDelivered();
    }
}