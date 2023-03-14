package r.nsu.fit.masud;

public class CourierThread extends Thread {
    private final Courier courier;
    private final Warehouse warehouse;

    public CourierThread(Courier courier, Warehouse warehouse) {
        this.courier = courier;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (!warehouse.isEmpty() || !courier.getPizzasToDeliver().isEmpty()) {
            Pizza pizza = warehouse.removePizza();
            if (pizza != null) {
                courier.addPizza(pizza);
            } else {
                try {
                    // Sleep for a short time to avoid busy waiting
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
