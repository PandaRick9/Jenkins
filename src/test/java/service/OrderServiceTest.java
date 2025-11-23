package service;

import model.Dish;
import model.Order;
import model.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class OrderServiceTest {

    private OrderService orderService;
    private NotificationService notificationService;
    private PaymentService paymentService;
    private DeliveryService deliveryService;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        paymentService = mock(PaymentService.class);
        deliveryService = mock(DeliveryService.class);

        orderService = new OrderService(notificationService, paymentService, deliveryService);

        restaurant = new Restaurant("Test Restaurant");
        restaurant.addDish(new Dish(1, "Пицца Маргарита", 550.0));
        restaurant.addDish(new Dish(2, "Паста Карбонара", 480.0));
    }

    @Test
    void testCreateOrder_Success() {
        when(paymentService.processPayment("Анна", 1030.0)).thenReturn(true);

        var result = orderService.createOrder("Анна", restaurant,
                List.of("Пицца Маргарита", "Паста Карбонара"));

        assertTrue(result.isPresent());
        verify(paymentService).processPayment("Анна", 1030.0);
        verify(deliveryService).deliver(any(Order.class), eq("Алексей"));
    }

    @Test
    void testCreateOrder_WithUnavailableDish() {
        restaurant.findDish("Пицца Маргарита").ifPresent(Dish::markUnavailable);

        var result = orderService.createOrder("Анна", restaurant,
                List.of("Пицца Маргарита"));

        assertFalse(result.isPresent());
        verify(notificationService).notifyUnavailable("Пицца Маргарита", "Анна");
    }

    @Test
    void testCreateOrder_DishNotFound() {
        var result = orderService.createOrder("Анна", restaurant,
                List.of("Несуществующее блюдо"));

        assertFalse(result.isPresent());
        verify(notificationService).notifyUnavailable("Несуществующее блюдо", "Анна");
    }

    @Test
    void testCreateOrder_PaymentFailed() {
        when(paymentService.processPayment("Анна", 550.0)).thenReturn(false);

        var result = orderService.createOrder("Анна", restaurant,
                List.of("Пицца Маргарита"));

        assertFalse(result.isPresent());
        verify(paymentService).processPayment("Анна", 550.0);
        verify(deliveryService, never()).deliver(any(), any());
    }
}