package r.nsu.fit.masud;

import java.util.LinkedList;

public class PizzaQueue {
    private LinkedList<Order> orders;

    public PizzaQueue() {
        orders = new LinkedList<>();
    }

    public void addOrder(Order order) {
        orders.addLast(order);
    }

    public Order getNextOrder() {
        return orders.poll();
    }

    public boolean hasOrders() {
        return !orders.isEmpty();
    }
}
