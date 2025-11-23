package model;

import exception.DishNotFoundException;
import exception.DuplicateDishException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RestaurantTest {

    @Test
    void testSafeFindDish_Success() {
        Restaurant restaurant = new Restaurant("Test");
        Dish expectedDish = new Dish(1, "Пицца", 500.0);
        restaurant.addDish(expectedDish);

        Dish result = restaurant.safeFindDish("Пицца");

        assertEquals(expectedDish, result);
    }

    @Test
    void testSafeFindDish_NotFound() {
        Restaurant restaurant = new Restaurant("Test");

        assertThrows(DishNotFoundException.class, () -> {
            restaurant.safeFindDish("Несуществующее блюдо");
        });
    }

    @Test
    void testSafeFindDish_Duplicate() {
        Restaurant restaurant = new Restaurant("Test");
        restaurant.addDish(new Dish(1, "Пицца", 500.0));
        restaurant.addDish(new Dish(2, "Пицца", 600.0));

        assertThrows(DuplicateDishException.class, () -> {
            restaurant.safeFindDish("Пицца");
        });
    }
}