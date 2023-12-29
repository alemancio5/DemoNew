package main.java.view;

import java.util.Objects;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import main.java.controller.Game;



public class GameView {
    private Group group;
    
    private Image boardImage;
    private ImageView boardView;
    
    private int columnPlayerImage = 1;
    private Image playerImage;
    private ImageView playerView;
    
    private Image overImage;
    private ImageView overView;
    
    private Scene scene;
    private boolean isMovingScene = false;
    private KeyCode keyMoveScene = KeyCode.S;
    private int translationMoveScene = 190;
    private int timeMoveScene = 200;
    private Timeline timelineMoveScene;


    
    public GameView() {
        // initializing the scene
        this.group = new Group();
        group.setStyle("-fx-background-color: black;");

        // initializing the board view
        this.initBoardView();
        this.group.getChildren().add(this.boardView);

        // initializing the player view
        this.initPlayerView();
        this.group.getChildren().add(this.playerView);

        // initializing the over view
        this.initOverView();
        this.group.getChildren().add(this.overView);

        // initializing the scene
        this.scene = new Scene(this.group);
        this.initScene();
    }
    
    private void initBoardView() {
        this.boardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/board_images/" + Game.board.getBoardname() + ".png")));
        this.boardView = new ImageView(this.boardImage);
        this.boardView.setX((View.stageColumns / 2) - (Game.player.getColumn() * View.tileColumns) - (View.tileColumns / 2));
        this.boardView.setY((View.stageRows / 2) - (Game.player.getRow() * View.tileRows) - (View.tileRows / 2));
    }
    
        public void refreshBoardView() {
            this.group.getChildren().remove(this.boardView);
            this.initBoardView();
            this.group.getChildren().add(this.boardView);
        } 

    public void moveBoardView(int rows, int columns) {
        double currentX = this.boardView.getTranslateX();
        double currentY = this.boardView.getTranslateY();

        TranslateTransition transition = new TranslateTransition(Duration.millis(this.translationMoveScene), this.boardView);
        transition.setToX(currentX + columns);
        transition.setToY(currentY + rows);
        transition.play();
    }

    private int selectRowPlayerImage(KeyCode key) {
        switch (key) {
            case KeyCode.W:
                return 3;
            case KeyCode.A:
                return 1;
            case KeyCode.S:
                return 0;
            case KeyCode.D:
                return 2;
            default:
                throw new IllegalStateException("Invalid key for row player image selection");
        }
    }

    private int selectColumnPlayerImage() {
        if (this.columnPlayerImage == 1) {
            this.columnPlayerImage = 3;
            return 1;
        }
        else if (this.columnPlayerImage == 3) {
            this.columnPlayerImage = 1;
            return 3;
        }
        else {
            throw new IllegalStateException("Invalid last frame for column player image selection");
        }
    }

    private void initPlayerView() {
        this.playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/skin_images/" + Game.player.getSkinname() + ".png")));
        this.playerView = new ImageView();
        this.setPlayerView(0, 0);
        this.playerView.setX((View.stageColumns / 2) - (View.tileColumns / 2));
        this.playerView.setY((View.stageRows / 2) - (3 * View.tileRows / 2));
    }

    public void refreshPlayerView() {
        this.group.getChildren().remove(this.playerView);
        int row = this.selectRowPlayerImage(this.keyMoveScene);
        this.setPlayerView(row, 0);
        this.group.getChildren().add(this.playerView);
    }

    private void setPlayerView(int row, int column) {
        WritableImage writableImage = new WritableImage(this.playerImage.getPixelReader(), column * View.tileColumns, row * View.tileRows * 2, View.tileColumns, View.tileRows * 2);
        this.playerView.setImage(writableImage);
    }

    private void initOverView() {
        this.overImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/over_images/" + Game.board.getBoardname() + ".png")));
        this.overView = new ImageView(this.overImage);
        this.overView.setX((View.stageColumns / 2) - (Game.player.getColumn() * View.tileColumns) - (View.tileColumns / 2));
        this.overView.setY((View.stageRows / 2) - (Game.player.getRow() * View.tileRows) - (View.tileRows / 2));
    }
    
        public void refreshOverView() {
            this.group.getChildren().remove(this.overView);
            this.initOverView();
            this.group.getChildren().add(this.overView);
        }

    public void moveOverView(int rows, int columns) {
        double currentX = this.overView.getTranslateX();
        double currentY = this.overView.getTranslateY();

        TranslateTransition transition = new TranslateTransition(Duration.millis(this.translationMoveScene), this.overView);
        transition.setToX(currentX + columns);
        transition.setToY(currentY + rows);
        transition.play();
    }

    private void initScene() {
        // setting the timeline
        this.timelineMoveScene = new Timeline(new KeyFrame(Duration.millis(this.timeMoveScene), event1 -> {
            Game.movePlayer(this.keyMoveScene);
        }));
        this.timelineMoveScene.setCycleCount(Timeline.INDEFINITE);
    
        // setting the key pressed event
        this.scene.setOnKeyPressed(event0 -> {
            KeyCode key = event0.getCode();

            if (key == KeyCode.W || key == KeyCode.A || key == KeyCode.S || key == KeyCode.D) {
                if (!this.isMovingScene) {
                    this.isMovingScene = true;
                    this.keyMoveScene = key;
                    this.timelineMoveScene.play();
                }
            }
            if (key == KeyCode.L) {
                Game.actionBoard(this.keyMoveScene);
            }
        });
        
        // setting the key released event
        this.scene.setOnKeyReleased(event0 -> {
            char key = event0.getCode().toString().charAt(0);

            if (key == 'W' || key == 'A' || key == 'S' || key == 'D') {
                this.isMovingScene = false;
                this.timelineMoveScene.pause();
                this.setPlayerView(this.selectRowPlayerImage(this.keyMoveScene), 0);
            }
        });
    }

    public Scene getScene() {
        return this.scene;
    }

    public void move(KeyCode key) {
        // setting the columns and rows of the transition
        int rows = 0;
        int columns = 0;
        switch (key) {
            case KeyCode.W:
                rows = View.tileRows;
                break;
            case KeyCode.A:
                columns = View.tileColumns;
                break;
            case KeyCode.S:
                rows = -View.tileRows;
                break;
            case KeyCode.D:
                columns = -View.tileColumns;
                break;
            default:
                throw new IllegalStateException("Invalid key for board view movement");
        }

        // setting the row and column of the player image
        int row = this.selectRowPlayerImage(key);
        int column = this.selectColumnPlayerImage();

        // moving the views
        this.moveBoardView(rows, columns);
        this.setPlayerView(row, column);
        this.moveOverView(rows, columns);
    }
}
