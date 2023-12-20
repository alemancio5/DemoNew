package main.java.view;

import java.util.Objects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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

    private int durationMoveBoardView = 150;
    private Image boardImage;
    private ImageView boardView;

    public static final int playerColumns = 32;
    public static final int playerRows = 48;
    private int durationMovePlayerView = 150;
    private int lastStepPlayerView = 1;
    private char directionMovePlayerView;
    private boolean isMovingPlayerView = false;
    private Timeline timelineMovePlayerView;
    private Image playerImage;
    private ImageView playerView;
    
    public GameView() {
        // initializing the scene
        this.group = new Group();
        this.initBoardView();
        this.initPlayerView();
        this.scene = new Scene(this.group);

        // initializing events handler thread
        this.scene.setOnKeyPressed(event1 -> {
            if (this.isMovingPlayerView == false) {
                this.isMovingPlayerView = true;

                this.directionMovePlayerView = event1.getCode().toString().charAt(0);
                Duration duration = Duration.millis(this.durationMovePlayerView);
                
                // creating the timeline
                this.timelineMovePlayerView = new Timeline(new KeyFrame(duration, event2 -> {
                    this.movePlayer(this.directionMovePlayerView);
                }));
                this.timelineMovePlayerView.setCycleCount(Timeline.INDEFINITE);
                this.timelineMovePlayerView.play();
            }
        });

        this.scene.setOnKeyReleased(event3 -> {
            this.timelineMovePlayerView.stop();
            this.setFramePlayerView(this.selectRowPlayerImage(this.directionMovePlayerView), 0);
            this.isMovingPlayerView = false;
        });
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
                throw new IllegalStateException("Invalid direction for board view movement");
        }

        // creating the animation
        TranslateTransition transition = new TranslateTransition(Duration.millis(this.durationMoveBoardView), this.boardView);
        transition.setByX(columns);
        transition.setByY(rows);
        transition.play();
    }

    private void initPlayerView() {
        this.playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/skin_images/" + App.player.getSkinname() + ".png")));
        this.initFramePlayerView(0, 0);
        this.playerView.setX((View.stageColumns / 2) - (View.tileColumns / 2));
        this.playerView.setY((View.stageRows / 2) - (3 * View.tileRows / 2));
        this.group.getChildren().add(this.playerView);
    }

    private int selectRowPlayerImage(char direction) {
        switch (direction) {
            case 'W':
                return 3;
            case 'A':
                return 1;
            case 'S':
                return 0;
            case 'D':
                return 2;
            default:
                throw new IllegalStateException("Invalid direction for player image selection");
        }
    }

    private void initFramePlayerView(int row, int column) {
        WritableImage writableImage = new WritableImage(this.playerImage.getPixelReader(), column * 32, row * 48, 32, 48);
        this.playerView = new ImageView(writableImage);
    }

    private void setFramePlayerView(int row, int column) {
        WritableImage writableImage = new WritableImage(this.playerImage.getPixelReader(), column * 32, row * 48, 32, 48);
        this.playerView.setImage(writableImage);
    }

    private void movePlayer(char direction) {
        System.out.println("Moving player " + direction);
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
        int row = this.selectRowPlayerImage(direction);
        
        // choosing the column of the image
        int column = 0;
        if (this.lastStepPlayerView == 1) {
            column = 3;
            this.lastStepPlayerView = 3;
        } else if (this.lastStepPlayerView == 3) {
            column = 1;
            this.lastStepPlayerView = 1;
        }

        // updating the player view
        this.setFramePlayerView(row, column);
    }

    public Scene getScene() {
        return this.scene;
    }
}
