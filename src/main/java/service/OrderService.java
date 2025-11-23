package service;

import config.Log;
import exception.DishNotFoundException;
import exception.DuplicateDishException;
import model.Dish;
import model.Order;
import model.Restaurant;

import java.util.List;
import java.util.Optional;

public class OrderService {
    private final NotificationService notifier;
    private final PaymentService paymentService;
    private final DeliveryService deliveryService;

    public OrderService(NotificationService notifier, PaymentService paymentService, DeliveryService deliveryService) {
        this.notifier = notifier;
        this.paymentService = paymentService;
        this.deliveryService = deliveryService;
    }

    public Optional<Order> createOrder(String customerName, Restaurant restaurant, List<String> dishNames) {
        Log.logger.info("Creating order for: " + customerName);
        Log.logger.fine("Searching for dishes in restaurant " + restaurant.getName());
        Order order = new Order(customerName);

        for (String dishName : dishNames) {
            try {
                Dish dish = restaurant.safeFindDish(dishName);
                if (!dish.isAvailable()) {
                    notifier.notifyUnavailable(dishName, customerName);
                    return Optional.empty();
                }
                order.addDish(dish);
            } catch (DishNotFoundException | DuplicateDishException e) {
                Log.logger.warning(e.getMessage());
                notifier.notifyUnavailable(dishName, customerName);
                return Optional.empty();
            }
        }

        if (paymentService.processPayment(customerName, order.getTotal())) {
            order.markPaid();
            deliveryService.deliver(order, "Alex");
            return Optional.of(order);
        }
        return Optional.empty();
    }
}