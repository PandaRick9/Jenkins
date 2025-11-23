package exception;

public class DuplicateDishException extends FoodDeliveryException {
    public DuplicateDishException(String name) {
        super("Found multiple dishes with name '" + name + "'");
    }
}