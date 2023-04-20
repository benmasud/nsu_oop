package r.masud.ccfit.nsu.ru;



public class Wall {
    private final Point[] blocks;

    public Wall(int maxX, int maxY) {
        blocks = new Point[maxX * 2 + maxY * 2];

        int i = 0;
        for (int x = 0; x < maxX; x++) {
            blocks[i++] = new Point(x, 0);
            blocks[i++] = new Point(x, maxY - 1);
        }

        for (int y = 1; y < maxY - 1; y++) {
            blocks[i++] = new Point(0, y);
            blocks[i++] = new Point(maxX - 1, y);
        }
    }

    public Point[] getBlocks() {
        return blocks;
    }
}


