package exception;

public class DuplicateDishException extends FoodDeliveryException {
    public DuplicateDishException(String name) {
        super("Найдено несколько блюд с названием '" + name + "'");
    }
}
