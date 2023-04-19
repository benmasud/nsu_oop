package r.masud.ccfit.nsu.ru.task_2_3_2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;



import javax.print.attribute.standard.Media;
import java.awt.Point;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

//    creating a frame
    private static final int WIDTH = 600;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS =30;
    private static final int COLUMNS =ROWS;

    private static final int SQUARE_SIZE = WIDTH/ROWS;
    private static final String[] FOOD_ICONS = new String[]{
            "C:\\Users\\Asus\\IdeaProjects\\oop\\Task_2_3_2\\src\\context\\carrot.png",
            "C:\\Users\\Asus\\IdeaProjects\\oop\\Task_2_3_2\\src\\context\\mushroom.png",
            "C:\\Users\\Asus\\IdeaProjects\\oop\\Task_2_3_2\\src\\context\\nut.png",
            "C:\\Users\\Asus\\IdeaProjects\\oop\\Task_2_3_2\\src\\context\\sulphite.png"
    };


    private static final int RIGHT =0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    private List<Point> snakeBody = new ArrayList();
    private Point snakeHead;
    private Image foodIcons;
    private int foodX;
    private int foodY;
    private boolean gameOver;
    private int currentDirection;
    private int score = 0;







    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Snake In Shell");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH,HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);



        Image icon = new Image("C:\\Users\\Asus\\IdeaProjects\\oop\\Task_2_3_2\\src\\context\\serpent.png");

        primaryStage.getIcons().add(icon);
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();




        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(gameOver){
                    restartGame();
                    return;
                }

                KeyCode code = event.getCode();
                switch(code){
                    case RIGHT:
                        currentDirection = RIGHT;
                        break;
                    case LEFT:
                        currentDirection = LEFT;
                        break;
                    case UP:
                        currentDirection = UP;
                        break;
                    case DOWN:
                        currentDirection = DOWN;
                        break;
                }

            }
        });

        for(int i=0; i<3; i++){
            snakeBody.add(new Point(5, ROWS/2));

        }


        snakeHead = snakeBody.get(0);
        generateFood();



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(140),e->run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }

    private void run(GraphicsContext gc){

        if(gameOver){
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("Poppins-10",20));
            gc.fillText("Game Over",WIDTH/3.5, HEIGHT / 2);
            gc.setFont(new Font("Poppins-10", 15));
            gc.fillText("Press any key to restart", WIDTH / 4, HEIGHT / 2 + 30);

            return;
        }
        drawBackground(gc);
        drawFood(gc);
        drawSnake(gc);
        drawScore();

        for(int i = snakeBody.size()-1; i>=1; i--){
            snakeBody.get(i).x = snakeBody.get(i-1).x;
            snakeBody.get(i).y = snakeBody.get(i-1).y;
        }

        switch(currentDirection){
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;

        }
        gameOver();
        eatFood();

    }

    private void drawBackground(GraphicsContext gc) {
        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLUMNS; j++){
                if((i+j)%2 ==0){
                gc.setFill(Color.web("#000000"));
            } else {
                    gc.setFill(Color.web("#000000"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

            }
        }
    }

    private void generateFood(){
        start:
        while(true){
            foodX = (int)(Math.random()*ROWS);
            foodY = (int)(Math.random()*COLUMNS);

            for(Point snake : snakeBody){
                if(snake.getX()== foodX && snake.getY() == foodY){
                    continue start;
                }
            }

            foodIcons = new Image(FOOD_ICONS[(int) (Math.random()*FOOD_ICONS.length)]);
            break;

        }
    }

    private void drawSnake(GraphicsContext gc){
        gc.setFill(Color.web("#FF0000"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 20,20);

        for(int i =1; i< snakeBody.size(); i++){
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE-1 , SQUARE_SIZE - 1, 15, 15);

        }

    }

    public void drawFood(GraphicsContext gc){
        gc.drawImage(foodIcons, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE,SQUARE_SIZE );
    }


//    Movement of the snake

    private void moveRight(){
        snakeHead.x++;
    }
    private void moveLeft(){
        snakeHead.x--;
    }
    private void moveUp(){
        snakeHead.y--;
    }
    private void moveDown(){
        snakeHead.y++;
    }


//    game ending


    public void gameOver(){
        if(snakeHead.x<0 || snakeHead.y <0 || snakeHead.x * SQUARE_SIZE >= WIDTH || snakeHead.y * SQUARE_SIZE >= HEIGHT){
            gameOver = true;
        }

        for(int i=1; i<snakeBody.size(); i++){
            if(snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()){
                gameOver = true;
                break;
            }
        }
    }

//    Eating food

    public void eatFood(){
        if(snakeHead.getX() == foodX && snakeHead.getY() == foodY){
            snakeBody.add(new Point(-1, -1));
            generateFood();
            score += 5;

        }
    }

    private void drawScore(){
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Poppins-10",20));
        gc.fillText("Score: "+score,10,35);
    }

//    Reset the game

    public void restartGame() {
        // Reset all game variables
        snakeBody.clear();
        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, ROWS / 2));
        }
        snakeHead = snakeBody.get(0);
        currentDirection = RIGHT;
        score = 0;
        gameOver = false;
        generateFood();
    }




    public static void main(String[] args){
        launch(args);
    }
}

