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
import javafx.util.Duration;

import main.java.app.App;



public class GameView {
    private Scene scene;
    private Group group;

    private int animationDurationBoardView = 40;
    private Image boardImage;
    private ImageView boardView;

    public static final int playerColumns = 32;
    public static final int playerRows = 48;
    private int animationDurationPlayerView = 20;
    private int lastUsedFramePlayerView = 1;
    private char directionPlayerView = 'S';
    private boolean isMovingPlayer = false;
    private Image playerImage;
    private ImageView playerView;
    
    public GameView() {
        // initializing the scene
        this.group = new Group();
        this.initBoardView();
        this.initPlayerView();
        this.scene = new Scene(this.group);

        // initializing events handler thread
        new Thread(() -> {
            this.scene.setOnKeyPressed(event -> {
                if (!this.isMovingPlayer) {
                    this.movePlayer(event.getCode().toString().charAt(0));
                }
            });
        }).start();
        new Thread(() -> {
            this.scene.setOnKeyReleased(event -> {
                this.isMovingPlayer = false;
            });
        }).start();
    }

    private void initBoardView() {
        this.boardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/board_images/" + App.board.getBoardname() + ".png")));
        this.boardView = new ImageView(this.boardImage);
        this.boardView.setX((View.stageColumns / 2) - (App.player.getColumn() * View.tileColumns) - (View.tileColumns / 2));
        this.boardView.setY((View.stageRows / 2) - (App.player.getRow() * View.tileRows) - (View.tileRows / 2));
        this.group.getChildren().add(this.boardView);
    }

    private void moveBoardView(char direction) {
        // setting the columns and rows of the transition
        int columns = 0;
        int rows = 0;
        switch (direction) {
            case 'W':
                rows = View.tileRows;
                break;
            case 'A':
                columns = View.tileColumns;
                break;
            case 'S':
                rows = -View.tileRows;
                break;
            case 'D':
                columns = -View.tileColumns;
                break;
            default:
                break;
        }

        // creating the animation
        TranslateTransition transition = new TranslateTransition(Duration.millis(this.animationDurationBoardView), this.boardView);
        transition.setByX(columns);
        transition.setByY(rows);
        transition.play();
    }

    private void initPlayerView() {
        this.playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/skin_images/" + App.player.getSkinname() + ".png")));
        this.playerView = new ImageView(this.extractPlayerImageFrame(0, 0));
        this.playerView.setX((View.stageColumns / 2) - (View.tileColumns / 2));
        this.playerView.setY((View.stageRows / 2) - (3 * View.tileRows / 2));
        this.group.getChildren().add(this.playerView);
    }

    private WritableImage extractPlayerImageFrame(int row, int column) {
        return new WritableImage(this.playerImage.getPixelReader(), column * 32, row * 48, 32, 48);
    }

    private void movePlayer(char direction) {
        switch (direction) {
            case 'W':
                if (App.board.isWalkable(App.player.getRow() - 1, App.player.getColumn())) {
                    App.player.move(direction);
                    this.movePlayerView(direction);
                    this.moveBoardView(direction);
                }
                break;
            case 'A':
                if (App.board.isWalkable(App.player.getRow(), App.player.getColumn() - 1)) {
                    App.player.move(direction);
                    this.movePlayerView(direction);
                    this.moveBoardView(direction);
                }
                break;
            case 'S':
                if (App.board.isWalkable(App.player.getRow() + 1, App.player.getColumn())) {
                    App.player.move(direction);
                    this.movePlayerView(direction);
                    this.moveBoardView(direction);
                }
                break;
            case 'D':
                if (App.board.isWalkable(App.player.getRow(), App.player.getColumn() + 1)) {
                    App.player.move(direction);
                    this.movePlayerView(direction);
                    this.moveBoardView(direction);
                }
                break;
            default:
                break;
        }
    }

    private void movePlayerView(char direction) {
        // setting the row of the image
        int row;
        switch (direction) {
            case 'W':
            row = 3;
            break;
            case 'A':
            row = 1;
            break;
            case 'S':
            row = 0;
            break;
            case 'D':
            row = 2;
            break;
            default:
            throw new IllegalStateException("Invalid direction for player view movement");
        }

        // making the player view face the right direction
        if (this.directionPlayerView != direction) {
            this.playerView.setImage(this.extractPlayerImageFrame(row, 0));
        }
        
        // choosing the column of the image
        int[] column = new int[1];
        if (this.lastUsedFramePlayerView == 1) {
            column[0] = 1;
            this.lastUsedFramePlayerView = 3;
        } else {
            column[0] = 3;
            this.lastUsedFramePlayerView = 1;
        }

        // creating the animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(this.animationDurationBoardView), event -> {
            this.playerView.setImage(this.extractPlayerImageFrame(row, column[0]));
            column[0] = 0;
        }));
        timeline.setCycleCount(2);
        this.isMovingPlayer = true;
        timeline.play();
        timeline.setOnFinished(event -> {
            this.directionPlayerView = direction;
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}
