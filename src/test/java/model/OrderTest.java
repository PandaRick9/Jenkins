package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class OrderTest {

    @Test
    void testOrderCreation() {
        Order order = new Order("Anna");

        assertEquals("Anna", order.getCustomerName());
        assertFalse(order.isPaid());
        assertFalse(order.isDelivered());
        assertEquals(0.0, order.getTotal());
        assertTrue(order.getItems().isEmpty());
    }

    @Test
    void testAddDishAndTotal() {
        Order order = new Order("Anna");
        Dish dish1 = new Dish(1, "Pizza", 500.0);
        Dish dish2 = new Dish(2, "Drink", 100.0);

        order.addDish(dish1);
        order.addDish(dish2);

        assertEquals(2, order.getItems().size());
        assertEquals(600.0, order.getTotal());
    }

    @Test
    void testOrderStatusChanges() {
        Order order = new Order("Anna");

        order.markPaid();
        order.markDelivered();

        assertTrue(order.isPaid());
        assertTrue(order.isDelivered());
    }
}