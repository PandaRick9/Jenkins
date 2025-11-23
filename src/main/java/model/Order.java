package model;


import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int counter = 0;
    private final int id;
    private final String customerName;
    private final List<Dish> items = new ArrayList<>();
    private boolean paid = false;
    private boolean delivered = false;

    public Order(String customerName) {
        this.id = ++counter;
        this.customerName = customerName;
    }

    public void addDish(Dish dish) {
        items.add(dish);
    }

    public double getTotal() {
        return items.stream().mapToDouble(Dish::getPrice).sum();
    }

    public void markPaid() {
        this.paid = true;
    }

    public void markDelivered() {
        this.delivered = true;
    }

    public boolean isPaid() {
        return paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public String toString() {
        return String.format("Order #%d for %s: %d dishes, total %.2f$ (%s, %s)",
                id, customerName, items.size(), getTotal(),
                paid ? "paid" : "not paid",
                delivered ? "delivered" : "not delivered");
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Order.counter = counter;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Dish> getItems() {
        return items;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}