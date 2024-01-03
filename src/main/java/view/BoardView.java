package main.java.view;

import java.util.Objects;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import main.java.ctrl.GameCtrl;



public class BoardView {
    private Image terrainImage;
    private ImageView terrainImageView;
    private Image overImage;
    private ImageView overImageView;

    private int moveTime = 190;



    public BoardView() {
        // initializing the terrain image view
        this.terrainImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/terrain_images/" + GameCtrl.getBoard().getBoardname() + ".png")));
        this.terrainImageView = new ImageView(this.terrainImage);
        this.terrainImageView.setX((View.stageColumns / 2) - (GameCtrl.getPlayer().getColumn() * View.tileColumns) - (View.tileColumns / 2));
        this.terrainImageView.setY((View.stageRows / 2) - (GameCtrl.getPlayer().getRow() * View.tileRows) - (View.tileRows / 2));

        // initializing the over image view
        this.overImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/over_images/" + GameCtrl.getBoard().getBoardname() + ".png")));
        this.overImageView = new ImageView(this.overImage);
        this.overImageView.setX((View.stageColumns / 2) - (GameCtrl.getPlayer().getColumn() * View.tileColumns) - (View.tileColumns / 2));
        this.overImageView.setY((View.stageRows / 2) - (GameCtrl.getPlayer().getRow() * View.tileRows) - (View.tileRows / 2));
    }

    public ImageView getTerrainImageView() {
        return this.terrainImageView;
    }

    public ImageView getOverImageView() {
        return this.overImageView;
    }

    public void move() {
        // setting the columns and rows of the movement
        int rows = 0;
        int columns = 0;
        switch (GameCtrl.getPlayer().getDirection()) {
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
                throw new IllegalStateException("Invalid direction");
        }

        // taking the current position of the terrain image view
        double x = this.terrainImageView.getTranslateX();
        double y = this.terrainImageView.getTranslateY();

        // moving the terrain image view
        TranslateTransition transitionTerrainImageView = new TranslateTransition(Duration.millis(this.moveTime), this.terrainImageView);
        transitionTerrainImageView.setToX(x + columns);
        transitionTerrainImageView.setToY(y + rows);
        transitionTerrainImageView.play();

        // moving the over image view
        TranslateTransition transitionOverImageView = new TranslateTransition(Duration.millis(this.moveTime), this.overImageView);
        transitionOverImageView.setToX(x + columns);
        transitionOverImageView.setToY(y + rows);
        transitionOverImageView.play();
    }
}
