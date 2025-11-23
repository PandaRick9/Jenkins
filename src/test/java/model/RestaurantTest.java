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
        Dish expectedDish = new Dish(1, "Pizza", 500.0);
        restaurant.addDish(expectedDish);

        Dish result = restaurant.safeFindDish("Pizza");

        assertEquals(expectedDish, result);
    }

    @Test
    void testSafeFindDish_NotFound() {
        Restaurant restaurant = new Restaurant("Test");

        assertThrows(DishNotFoundException.class, () -> {
            restaurant.safeFindDish("Non-existent dish");
        });
    }

    @Test
    void testSafeFindDish_Duplicate() {
        Restaurant restaurant = new Restaurant("Test");
        restaurant.addDish(new Dish(1, "Pizza", 500.0));
        restaurant.addDish(new Dish(2, "Pizza", 600.0));

        assertThrows(DuplicateDishException.class, () -> {
            restaurant.safeFindDish("Pizza");
        });
    }
}