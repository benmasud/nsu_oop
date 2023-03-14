package r.nsu.fit.masud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Read JSON input file
        JsonParser parser = new JsonParser();
        try (FileReader reader = new FileReader("C:\\Users\\Asus\\IdeaProjects\\oop\\Task_2_2_1\\MeinePizza.json")) {
            JsonElement root = parser.parse(reader);
            JsonArray ordersJsonArray = root.getAsJsonArray();

            // Convert JSON to objects
            Gson gson = new GsonBuilder().create();
            List<Order> orders = new ArrayList<>();
            for (JsonElement element : ordersJsonArray) {
                Order order = gson.fromJson(element, Order.class);
                orders.add(order);
            }

            // Create objects
            PizzaQueue pizzaQueue = new PizzaQueue();
            Warehouse warehouse = new Warehouse(100);
            Courier courier = new Courier(50);

            for (Order order : orders) {
                // Add order to queue
                pizzaQueue.addOrder(order);

                // Add pizzas to warehouse
                for (int i = 0; i < order.getNumPizzas(); i++) {
                    Pizza pizza = new Pizza();
                    warehouse.addPizza(pizza);
                }
            }

            // Start bakery and courier threads
            Bakery bakery = new Bakery(pizzaQueue, warehouse);
            CourierThread courierThread = new CourierThread(courier, warehouse);
            bakery.start();
            courierThread.start();

            // Wait for threads to finish
            try {
                bakery.join();
                courierThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Print delivered orders
            System.out.println("Delivered orders:");
            for (Order order : courier.getDeliveredOrders()) {
                System.out.println("Order #" + order.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
