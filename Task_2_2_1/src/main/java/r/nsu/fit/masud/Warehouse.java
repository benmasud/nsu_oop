package r.nsu.fit.masud;

import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {
    private final int capacity;
    private final Queue<Pizza> pizzas;

    public Warehouse(int capacity) {
        this.capacity = capacity;
        this.pizzas = new LinkedList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumPizzas() {
        return pizzas.size();
    }

    public boolean isFull() {
        return pizzas.size() >= capacity;
    }

    public boolean isEmpty() {
        return pizzas.isEmpty();
    }

    public boolean addPizza(Pizza pizza) {
        if (isFull()) {
            return false;
        }
        pizzas.add(pizza);
        return true;
    }

    public Pizza removePizza() {
        if (isEmpty()) {
            return null;
        }
        return pizzas.remove();
    }

    public Order takeOrder() {
        
        return null;
    }

    public void returnOrder(Order order) {
    }
}

