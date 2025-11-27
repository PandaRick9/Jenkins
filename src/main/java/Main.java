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
        Log.logger.info("=== Starting Food Delivery System ===");
        NotificationService notifier = new ConsoleNotificationService();
        PaymentService paymentService = new SimplePaymentService();
        DeliveryService deliveryService = new DeliveryService();
        OrderService orderService = new OrderService(notifier, paymentService, deliveryService);

        Admin admin = new Admin();
        Restaurant rest = new Restaurant("PizzaHouse");

        admin.addDishToRestaurant(rest, new Dish(1, "Pizza Margherita", 550));
        admin.addDishToRestaurant(rest, new Dish(2, "Pasta Carbonara", 480));

        Customer customer = new Customer("Anna", orderService);
        customer.makeOrder(rest, List.of("Pizza Margherita", "Pasta Carbonara"));

        rest.findDish("Pizza Margherita").ifPresent(Dish::markUnavailable);
        customer.makeOrder(rest, List.of("Pizza Margherita"));
        System.out.println("Branch works");
        System.out.println("new logic develop");
    }
}