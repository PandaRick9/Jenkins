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
        Log.logger.info("Customer " + name + " is making an order");
        System.out.println(name + " is placing an order...");
        var orderOpt = orderService.createOrder(name, restaurant, dishes);
        orderOpt.ifPresentOrElse(
                order -> {
                    Log.logger.info("Order created successfully");
                    System.out.println(order);
                },
                () -> {
                    Log.logger.severe("Failed to create order");
                    System.out.println("Order was not created");
                }
        );
    }
}