import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PizzaDeliverySystem {
    private static final int MAX_WAREHOUSE_SIZE = 100; // maximum number of pizzas that can be stored in the warehouse
    private static final int MAX_TRUNK_CAPACITY = 10; // maximum number of pizzas that a courier can carry in one trip

    private final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<Pizza> warehouse = new LinkedBlockingQueue<>(MAX_WAREHOUSE_SIZE);

    private final AtomicInteger orderCounter = new AtomicInteger(0);

    private final Baker[] bakers;
    private final Courier[] couriers;

    public PizzaDeliverySystem(int numBakers, int numCouriers) {
        this.bakers = new Baker[numBakers];
        for (int i = 0; i < numBakers; i++) {
            this.bakers[i] = new Baker("Baker " + i);
            this.bakers[i].start();
        }

        this.couriers = new Courier[numCouriers];
        for (int i = 0; i < numCouriers; i++) {
            this.couriers[i] = new Courier("Courier " + i);
            this.couriers[i].start();
        }
    }

    public void submitOrder(Order order) throws InterruptedException {
        order.setId(orderCounter.incrementAndGet());
        orderQueue.put(order);
    }

    private void bakePizza(Order order) throws InterruptedException {
        Thread.sleep(order.getBakeTime()); // simulate baking time
        Pizza pizza = new Pizza(order);
        warehouse.put(pizza);
    }

    private void deliverPizza(Courier courier) throws InterruptedException {
        int pizzasDelivered = 0;
        while (pizzasDelivered < MAX_TRUNK_CAPACITY) {
            Pizza pizza = warehouse.poll(1, TimeUnit.SECONDS);
            if (pizza == null) {
                break; // no more pizzas available in warehouse
            }
            courier.deliverPizza(pizza);
            pizzasDelivered++;
        }
        courier.setAvailable(true);
    }

    private class Baker extends Thread {
        public Baker(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Order order = orderQueue.take();
                    System.out.printf("%s starts baking order %d\n", getName(), order.getId());
                    bakePizza(order);
                    System.out.printf("%s finishes baking order %d\n", getName(), order.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Courier extends Thread {
        private boolean available = true;

        public Courier(String name) {
            super(name);
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public void deliverPizza(Pizza pizza) throws InterruptedException {
            System.out.printf("%s starts delivering order %d\n", getName(), pizza.getOrder().getId());
            Thread.sleep(pizza.getOrder().getDeliveryTime()); // simulate delivery time
            System.out.printf("%s finishes delivering order %d\n", getName(), pizza.getOrder().getId());
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (available) {
                        System.out.printf("%s checks for pizzas to deliver\n", getName());
                        deliverPizza(this);
                    }
                    Thread.sleep(100); // check for available pizzas every 100 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
