package model;

import config.Log;
import exception.DishNotFoundException;
import exception.DuplicateDishException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Restaurant {
    private final String name;
    private final List<Dish> menu = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public void addDish(Dish dish) {
        menu.add(dish);
    }

    public Optional<Dish> findDish(String name) {
        return menu.stream().filter(d -> d.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Dish safeFindDish(String name) throws DishNotFoundException, DuplicateDishException {
        List<Dish> found = menu.stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .toList();
        if (found.isEmpty())
            throw new DishNotFoundException(name);
        if (found.size() > 1)
            throw new DuplicateDishException(name);
        return found.get(0);
    }

    public String getName() {
        return name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "Restaurant: " + name;
    }
}