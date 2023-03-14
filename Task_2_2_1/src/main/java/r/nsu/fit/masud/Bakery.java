package r.nsu.fit.masud;

public class Bakery implements Runnable {
    private final PizzaQueue pizzaQueue;
    private final Warehouse warehouse;
    private Thread thread;

    public Bakery(PizzaQueue pizzaQueue, Warehouse warehouse) {
        this.pizzaQueue = pizzaQueue;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            Order order = pizzaQueue.getNextOrder();
            if (order == null) {
                break;
            }

            // Bake pizzas
            for (int i = 0; i < order.getNumPizzas(); i++) {
                Pizza pizza = new Pizza();
                warehouse.addPizza(pizza);
                try {
                    // Add a sleep time of 1 second to simulate baking time
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
