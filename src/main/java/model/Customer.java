package model;

import config.Log;
import service.OrderService;

import java.util.List;

public class Customer {
    private final String name;
    private final OrderService orderService;

    public Customer(String name, OrderService orderService) {
        this.name = name;
        this.orderService = orderService;
    }

    public void makeOrder(Restaurant restaurant, List<String> dishes) {
        Log.logger.info("Клиент " + name + " делает заказ");
        System.out.println(name + " оформляет заказ...");

        var orderOpt = orderService.createOrder(name, restaurant, dishes);

        orderOpt.ifPresentOrElse(
                order -> {
                    Log.logger.info("Заказ успешно создан");
                    System.out.println(order);
                },
                () -> {
                    Log.logger.severe("Заказ создать не удалось");
                    System.out.println("Заказ не был создан");
                }
        );
    }
}
