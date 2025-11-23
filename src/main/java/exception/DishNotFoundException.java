package exception;

public class DishNotFoundException extends FoodDeliveryException {
    public DishNotFoundException(String name) {
        super("Dish '" + name + "' not found");
    }
}
