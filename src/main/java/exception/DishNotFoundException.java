package exception;

public class DishNotFoundException extends FoodDeliveryException {
    public DishNotFoundException(String name) {
        super("Блюдо '" + name + "' не найдено");
    }
}
