package main.java.view;

import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import main.java.ctrl.GameCtrl;



public class PlayerView {
    private Image skinImage;
    private WritableImage skinWritableImage;
    private ImageView skinImageView;
    private int step = 1;



    public PlayerView() {
        this.skinImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/skin_images/" + GameCtrl.getPlayer().getSkinname() + ".png")));
        this.skinWritableImage = new WritableImage(this.skinImage.getPixelReader(), 0 * View.tileColumns, 0 * View.tileRows * 2, View.tileColumns, View.tileRows * 2);
        this.skinImageView = new ImageView(this.skinWritableImage);
        this.skinImageView.setX((View.stageColumns / 2) - (View.tileColumns / 2));
        this.skinImageView.setY((View.stageRows / 2) - (3 * View.tileRows / 2));
    }

    public ImageView getSkinImageView() {
        return this.skinImageView;
    }
 
    public int getFace() {
        switch (GameCtrl.getPlayer().getDirection()) {
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

    public int getStep() {
        if (this.step == 1) {
            this.step = 3;
            return 1;
        }
        else if (this.step == 3) {
            this.step = 1;
            return 3;
        }
        else {
            throw new IllegalStateException("Invalid last step value");
        }
    }

    public void move() {
        int row = getFace();
        int column = getStep();
        this.skinWritableImage = new WritableImage(this.skinImage.getPixelReader(), column * View.tileColumns, row * View.tileRows * 2, View.tileColumns, View.tileRows * 2);
        this.skinImageView.setImage(this.skinWritableImage);
    }

    public void stop() {
        int row = getFace();
        int column = 0;
        this.skinWritableImage = new WritableImage(this.skinImage.getPixelReader(), column * View.tileColumns, row * View.tileRows * 2, View.tileColumns, View.tileRows * 2);
        this.skinImageView.setImage(this.skinWritableImage);
    }
}
