package r.masud.ccfit.nsu.ru;



import java.util.Random;

public class Food {
    private Point location;

    public Food(int maxX, int maxY) {
        Random random = new Random();
        int x = random.nextInt(maxX);
        int y = random.nextInt(maxY);
        location = new Point(x, y);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
