package r.masud.ccfit.nsu.ru;


import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private int direction;

    public Snake(Point head) {
        body = new LinkedList<>();
        body.add(head);
        direction = Direction.RIGHT;
    }

    public void move() {
        Point head = body.getFirst();
        Point newHead = new Point(head.x, head.y);

        switch (direction) {
            case Direction.UP:
                newHead.y--;
                break;
            case Direction.DOWN:
                newHead.y++;
                break;
            case Direction.LEFT:
                newHead.x--;
                break;
            case Direction.RIGHT:
                newHead.x++;
                break;
        }

        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow() {
        Point tail = body.getLast();
        body.addLast(new Point(tail.x, tail.y));
    }

    public boolean collidesWith(Point point) {
        for (Point p : body) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfBounds(int maxX, int maxY) {
        Point head = body.getFirst();
        return head.x < 0 || head.y < 0 || head.x >= maxX || head.y >= maxY;
    }

    public boolean bitesItself() {
        Point head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            Point point = body.get(i);
            if (head.equals(point)) {
                return true;
            }
        }
        return false;
    }

    public void setDirection(int direction) {
        if (Math.abs(this.direction - direction) != 2) {
            this.direction = direction;
        }
    }

    public LinkedList<Point> getBody() {
        return body;
    }
}

