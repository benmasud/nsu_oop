package r.masud.ccfit.nsu.ru.task_2_3_1_;

import java.awt.Point;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import r.masud.ccfit.nsu.ru.task_2_3_1_.controller.GameController;
import r.masud.ccfit.nsu.ru.task_2_3_1_.controller.json.JsonParser;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.CollisionManager;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.Food;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.MovementManager;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.Walls;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.json.JsonData;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.snakes.EnemySnakeFood;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.snakes.EnemySnakeRandom;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.snakes.Snake;
import r.masud.ccfit.nsu.ru.task_2_3_1_.view.GameField;
import r.masud.ccfit.nsu.ru.task_2_3_1_.view.Graphics;
import r.masud.ccfit.nsu.ru.task_2_3_1_.view.SnakeGameView;


public class SnakeGame extends Application {

    static JsonData jsonData = new JsonParser().getData("info.json");
    private static final int width = jsonData.width();

    private static final int height = jsonData.height();
    private static final int columns = jsonData.columns();
    private static final int rows = jsonData.rows();
    private static final int squareSize = jsonData.squareSize();
    private int scoreForWin = 20;
    private Graphics graphics;

    /**
     * Represents the possible directions for the enemy snake.
     */
    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    private Direction direction;

    private GraphicsContext gc;
    private GraphicsContext gcInfo;

    Walls walls  = new Walls();
    private int score = 0;
    private GameField gameField;
    private Food food;

    int maxFood = 10;
    int speed = 130;
    Snake snake;
    private Integer gameLevel = 2;

    EnemySnakeRandom enemySnakeRandom;
    EnemySnakeFood enemySnakeFood;

    GameController gameController = new GameController();
    SnakeGameView snakeGameView = new SnakeGameView();


    @Override
    public void start(Stage primaryStage) {

        Label level;
        final Spinner<Integer> spinner = new Spinner<>();
        final int initialValue = 20;
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 50, initialValue);
        spinner.setValueFactory(valueFactory);

        Button buttonLevel = new Button();
        Button buttonLevel2 = new Button();
        Button buttonLevel3 = new Button();
        level = gameController.handleLevelButton(buttonLevel, buttonLevel2, buttonLevel3);

        Button button = new Button();
        button.setText("Start game");

        button.setLayoutX(30);
        button.setLayoutY(50);

        Label finalLevel = level;
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String currentLevelText = finalLevel.getText();
                switch (currentLevelText) {
                    case "You chose level 1" -> gameLevel = 1;
                    case "You chose level 3" -> gameLevel = 3;
                    default -> gameLevel = 2;
                }

                if (gameLevel == 1) {
                    maxFood = jsonData.maxFoodForLevel1();
                    speed = jsonData.speedFoodForLevel1();
                    walls.addWalls(jsonData.wallsFoodForLevel1());
                } else if (gameLevel == 2) {
                    maxFood = jsonData.maxFoodForLevel2();
                    speed = jsonData.speedFoodForLevel2();
                    walls.addWalls(jsonData.wallsFoodForLevel2());
                } else if (gameLevel == 3) {
                    maxFood = jsonData.maxFoodForLevel3();
                    speed = jsonData.speedFoodForLevel3();
                    walls.addWalls(jsonData.wallsFoodForLevel3());
                }
                scoreForWin = spinner.getValue();


                gameField = new GameField(width, height, columns, rows, squareSize);
                food = new Food(gameField, maxFood);
                snake = new Snake(gameField, food, walls);
                graphics = new Graphics(gameField, width, height, columns, rows);

                food.addSnake(snake);

                Scene scene = snakeGameView.createScene(width, height);

                primaryStage.setScene(scene);
                primaryStage.setX(300);
                primaryStage.setY(20);
                primaryStage.show();

                gc = snakeGameView.getGraphicsContext();
                gcInfo = snakeGameView.getInfoGraphicsContext();

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        KeyCode code = event.getCode();
                        direction = gameController.handleKeyPress(code, direction);
                    }
                });

                snake.gameOver();

                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(speed),
                        e -> run(gc, gcInfo)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();

                enemySnakeRandom = new EnemySnakeRandom(gameField, food, walls);
                enemySnakeRandom.initSnake();
                enemySnakeFood = new EnemySnakeFood(gameField, food, walls);
                enemySnakeFood.initSnake();
                food.addEnemySnakeFood(enemySnakeFood);
                food.addEnemySnakeRandom(enemySnakeRandom);

                food.generateFood(walls);
            }
        });

        Scene menuScene = snakeGameView.createMenuScene(spinner, level, button,
                buttonLevel, buttonLevel2, buttonLevel3);

        primaryStage.setTitle("Snake game menu");
        primaryStage.setScene(menuScene);
        primaryStage.show();

    }

    /**
     * The main game loop.
     *
     * @param gc     the GraphicsContext for the game canvas
     * @param gcInfo the GraphicsContext for the info canvas
     */
    private void run(GraphicsContext gc, GraphicsContext gcInfo) {
        CollisionManager collisionManager = new CollisionManager();
        collisionManager.collision(snake, enemySnakeFood, enemySnakeRandom);

        if (enemySnakeFood.getScore() == scoreForWin
                || enemySnakeRandom.getScore() == scoreForWin) {
            snake.setGameOver();
        }
        if (snake.isGameOver()) {
            graphics.drawGameOver(gc);
            return;
        }
        if (score == scoreForWin) {
            graphics.drawGameWon(gc);
            return;
        }
        graphics.drawBackground(gc);
        graphics.drawWalls(gc, walls.getWalls());
        graphics.drawSnake(gc, snake.getSnake(), 1);

        snake.eatFood();
        graphics.drawFood(gc, food.getFood());

        score = snake.getScore();
        int scoreEnemyFood = enemySnakeFood.getScore();
        int scoreEnemyRandom = enemySnakeRandom.getScore();
        graphics.drawScore(gcInfo, score, scoreEnemyFood, scoreEnemyRandom, scoreForWin);
        graphics.drawLevel(gcInfo, gameLevel);

        if (!snake.isGameOver()) {
            graphics.drawSnake(gc, enemySnakeRandom.getSnake(), 2);
            graphics.drawSnake(gc, enemySnakeFood.getSnake(), 3);
        }

        if (snake.getSnake().size() > 1) {
            Point crawling = snake.getSnake().get(snake.getSnake().size() - 1);
            crawling.x = snake.getSnakeHead().x;
            crawling.y = snake.getSnakeHead().y;
            snake.getSnake().add(1, crawling);
            snake.getSnake().remove(snake.getSnake().size() - 1);
        }

        MovementManager movementManager = new MovementManager();
        movementManager.updateSnakePosition(snake);
        if (direction != null) {
            movementManager.moveSnake(snake, direction);
        }

        snake.gameOver();

        if (!snake.isGameOver()) {
            movementManager.moveEnemySnakes(enemySnakeRandom, enemySnakeFood);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
