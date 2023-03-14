package r.nsu.fit.masud;

import java.util.ArrayList;
import java.util.List;

public class Courier {
    private int maxTrunkVolume;
    private List<Order> deliveredOrders;
    private List<Pizza> pizzasToDeliver;

    public Courier(int maxTrunkVolume) {
        this.maxTrunkVolume = maxTrunkVolume;
        this.deliveredOrders = new ArrayList<>();
        this.pizzasToDeliver = new ArrayList<>();
    }

    public List<Order> getDeliveredOrders() {
        return deliveredOrders;
    }

    public void deliver(Warehouse warehouse) {
        int currentTrunkVolume = 0;
        List<Order> ordersToDeliver = new ArrayList<>();

        // Find orders that fit in the courier's trunk
        while (currentTrunkVolume < maxTrunkVolume && !warehouse.isEmpty()) {
            Order order = warehouse.takeOrder();
            if (maxTrunkVolume - currentTrunkVolume >= order.size) {
                ordersToDeliver.add(order);
                currentTrunkVolume += order.size;
            } else {
                warehouse.returnOrder(order);
            }
        }

        // Deliver orders
        for (Order order : ordersToDeliver) {
            System.out.println("Order #" + order.getNumber() + " delivered by courier.");
            deliveredOrders.add(order);
            try {
                Thread.sleep(1000); // Wait for 1 second to simulate delivery time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPizza(Pizza pizza) {
        pizzasToDeliver.add(pizza);
    }

    public List<Pizza> getPizzasToDeliver() {
        List<Pizza> result = new ArrayList<>(pizzasToDeliver);
        pizzasToDeliver.clear();
        return result;
    }
}
