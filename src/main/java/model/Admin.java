package model;

import config.Log;

public class Admin {
    public void addDishToRestaurant(Restaurant r, Dish d) {
        Log.logger.fine("Администратор добавил блюдо: " + d.getName());
        r.addDish(d);
    }
}
