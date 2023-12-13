package main.java.view;

import java.util.Objects;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.app.App;



public class GameView {
    private Scene scene;
    private Group group;
    private ImageView playerView;
    private ImageView boardView;
    
    public GameView() {
        this.group = new Group();
        this.addBoardView();
        this.addPlayerView();
        this.scene = new Scene(this.group);
        this.addSceneHandler();
    }

    private void addBoardView() {
        Image boardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/board_images/" + App.board.getBoardname() + ".png")));
        this.boardView = new ImageView(boardImage);
        this.boardView.setX((View.stageColumns / 2) - (App.player.getColumn() * View.tileColumns) - (View.tileColumns / 2));
        this.boardView.setY((View.stageRows / 2) - (App.player.getRow() * View.tileRows) - (View.tileRows / 2));
        this.group.getChildren().add(this.boardView);
    }

    private void addPlayerView() {
        Image playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/skin_images/" + App.player.getSkinname() + ".png")));
        this.playerView = new ImageView(playerImage);
        this.playerView.setX((View.stageColumns / 2) - (View.tileColumns / 2));
        this.playerView.setY((View.stageRows / 2) - (3 * View.tileRows / 2));
        this.group.getChildren().add(this.playerView);
    }

    private void addSceneHandler() {
        this.scene.setOnKeyPressed(event -> {
            new Thread(() -> {
                switch (event.getCode()) {
                    case W:
                        if (App.board.isWalkable(App.player.getRow() - 1, App.player.getColumn())) {
                            App.player.moveUp();
                            boardView.setY(boardView.getY() + View.tileRows);
                        }
                        break;
                    case A:
                        if (App.board.isWalkable(App.player.getRow(), App.player.getColumn() - 1)) {
                            App.player.moveLeft();
                            boardView.setX(boardView.getX() + View.tileColumns);
                        }
                        break;
                    case S:
                        if (App.board.isWalkable(App.player.getRow() + 1, App.player.getColumn())) {
                            App.player.moveDown();
                            boardView.setY(boardView.getY() - View.tileRows);
                        }
                        break;
                    case D:
                        if (App.board.isWalkable(App.player.getRow(), App.player.getColumn() + 1)) {
                            App.player.moveRight();
                            boardView.setX(boardView.getX() - View.tileColumns);
                        }
                        break;
                    default:
                        break;
                }
            }).start();
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}
