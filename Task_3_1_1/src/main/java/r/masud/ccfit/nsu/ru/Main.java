package r.masud.ccfit.nsu.ru;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the size of the game
        System.out.print("Enter the width of the game: ");
        int width = scanner.nextInt();
        System.out.print("Enter the height of the game: ");
        int height = scanner.nextInt();

        // Create the game objects
        Snake snake = new Snake(new Point(width / 2, height / 2));
        Food food = new Food(width, height);
        Wall wall = new Wall(width, height);

        // Set up the game loop
        boolean running = true;
        int score = 0;
        while (running) {
            // Clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Draw the game board
            drawBoard(width, height, snake, food, wall);

            // Get the user input
            int direction = getDirectionFromInput(scanner);
            if (direction != 0) {
                snake.setDirection(direction);
            }

            // Move the snake
            snake.move();

            // Check if the snake hit a wall
            if (snake.isOutOfBounds(width, height)) {
                running = false;
                System.out.println("Game over: hit a wall!");
            }

            // Check if the snake hit itself
            if (snake.bitesItself()) {
                running = false;
                System.out.println("Game over: bit itself!");
            }

            // Check if the snake ate the food
            if (snake.collidesWith(food.getLocation())) {
                snake.grow();
                food.setLocation(new Food(width, height).getLocation());
                score++;
            }

            // Pause the game for a moment
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the final score
        System.out.println("Your score is: " + score);
    }

    public static void drawBoard(int width, int height, Snake snake, Food food, Wall wall) {
        Point[] blocks = wall.getBlocks();
        boolean[][] board = new boolean[height][width];

        // Draw the wall
        for (Point block : blocks) {
            if (block != null) { // <-- Add this check
                board[block.y][block.x] = true;
            }
        }

        // Draw the snake
        for (Point block : snake.getBody()) {
            if (block != null) { // <-- Add this check
                board[block.y][block.x] = true;
            }
        }

        // Draw the food
        Point foodLocation = food.getLocation();
        if (foodLocation != null) { // <-- Add this check
            board[foodLocation.y][foodLocation.x] = true;
        }

        // Draw the board
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(board[y][x] ? "*" : " ");
            }
            System.out.println();
        }
    }

    public static int getDirectionFromInput(Scanner scanner) {
        if (scanner.hasNext()) {
            String input = scanner.next().toLowerCase();
            switch (input) {
                case "w":
                    return Direction.UP;
                case "a":
                    return Direction.LEFT;
                case "s":
                    return Direction.DOWN;
                case "d":
                    return Direction.RIGHT;
                case "p":
                    System.out.println("Paused. Press any key to resume.");
                    scanner.next();
                    return 0;
                default:
                    return 0;
            }
        }
        return 0;
    }
}
