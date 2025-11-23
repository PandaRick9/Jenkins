import config.Log;
import model.Admin;
import service.ConsoleNotificationService;
import model.Customer;
import service.DeliveryService;
import model.Dish;
import service.OrderService;
import model.Restaurant;
import service.SimplePaymentService;
import service.NotificationService;
import service.PaymentService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Log.logger.info("=== Запуск системы доставки еды ===");

        NotificationService notifier = new ConsoleNotificationService();
        PaymentService paymentService = new SimplePaymentService();
        DeliveryService deliveryService = new DeliveryService();
        OrderService orderService = new OrderService(notifier, paymentService, deliveryService);

        Admin admin = new Admin();
        Restaurant rest = new Restaurant("ПиццаХаус");

        admin.addDishToRestaurant(rest, new Dish(1, "Пицца Маргарита", 550));
        admin.addDishToRestaurant(rest, new Dish(2, "Паста Карбонара", 480));

        Customer customer = new Customer("Анна", orderService);

        customer.makeOrder(rest, List.of("Пицца Маргарита", "Паста Карбонара"));

        rest.findDish("Пицца Маргарита").ifPresent(Dish::markUnavailable);
        customer.makeOrder(rest, List.of("Пицца Маргарита"));
    }
}
