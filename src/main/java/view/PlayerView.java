package main.java.view;

import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import main.java.controller.Game;

public class PlayerView {
    private int columnPlayerImage = 1;
    private Image playerImage;
    private ImageView playerView;

    public PlayerView() {
        this.playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/skin_images/" + Game.player.getSkinname() + ".png")));
        this.playerView = new ImageView();
        this.setPlayerView(0, 0);
        this.playerView.setX((View.stageColumns / 2) - (View.tileColumns / 2));
        this.playerView.setY((View.stageRows / 2) - (3 * View.tileRows / 2));
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
}
