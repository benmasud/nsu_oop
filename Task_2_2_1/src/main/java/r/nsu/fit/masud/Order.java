package r.nsu.fit.masud;

public class Order {
    private final int id;
    private final int numPizzas;
    public int size;

    public Order(int id, int numPizzas) {
        this.id = id;
        this.numPizzas = numPizzas;
    }

    public int getId() {
        return id;
    }

    public int getNumPizzas() {
        return numPizzas;
    }

    public boolean getSize() {

        return false;
    }

    public String getNumber() {
        return null;
    }
}

