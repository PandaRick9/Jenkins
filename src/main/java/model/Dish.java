package model;

public class Dish {
    private final int id;
    private final String name;
    private final double price;
    private boolean available = true;

    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markUnavailable() {
        this.available = false;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f$) %s", name, price, available ? "[Available]" : "[Unavailable]");
    }
}


