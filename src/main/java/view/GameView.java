package main.java.view;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.java.ctrl.GameCtrl;



public class GameView {
    private PlayerView playerView;
    private BoardView boardView;

    private int dialogIndex;
    private ArrayList<String> dialogList;
    private DialogPane dialogPane;

    private Group backGroup;
    private Group gameGroup;
    
    private Scene scene;
    
    private boolean dialoging = false;

    private boolean moving = false;
    private int moveTime = 150;
    private Timeline moveTimeline;


    
    public GameView() {
        // initializing the model views
        this.playerView = new PlayerView();
        this.boardView = new BoardView();

        // initializing the dialog pane
        this.dialogIndex = 0;
        this.dialogList = new ArrayList<>();
        this.dialogPane = new DialogPane();
        this.dialogPane.setVisible(this.dialoging);
        this.dialogPane.setPrefSize(View.stageColumns, 2.5 * View.tileRows);
        this.dialogPane.setLayoutX(0);
        this.dialogPane.setLayoutY(View.stageRows - 2.5 * View.tileRows);

        // initializing the groups
        this.backGroup = new Group();
        this.backGroup.getChildren().add(new Rectangle(View.stageColumns, View.stageRows, Color.BLACK));
        this.gameGroup = new Group();
        this.backGroup.getChildren().add(this.gameGroup);
        this.gameGroup.getChildren().add(this.boardView.getTerrainImageView());
        this.gameGroup.getChildren().add(this.playerView.getSkinImageView());
        this.gameGroup.getChildren().add(this.boardView.getOverImageView());
        this.gameGroup.getChildren().add(this.dialogPane);

        // initializing the scene
        this.scene = new Scene(this.backGroup);

        // setting the key pressed event
        this.scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            if (this.dialoging) {    // dialog
                if (key == KeyCode.L) {
                    if (this.dialogIndex == this.dialogList.size() - 1) {
                        this.dialoging = false;
                        this.dialogPane.setVisible(this.dialoging);
                    } else {
                        this.dialogIndex++;
                        this.dialogPane.setContentText(this.dialogList.get(this.dialogIndex));
                    }
                    return;
                }
                else {
                    return;
                }
            }
            if (key == KeyCode.W || key == KeyCode.A || key == KeyCode.S || key == KeyCode.D) {     // move
                if (!this.moving) {
                    this.moving = true;
                    GameCtrl.getPlayer().setDirection(key);
                    this.moveTimeline.play();
                }
            }
            if (key == KeyCode.L) {     // action
                GameCtrl.action();
            }
        });

        // setting the key released event
        this.scene.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();
            if (key == KeyCode.W || key == KeyCode.A || key == KeyCode.S || key == KeyCode.D) {     // stop
                this.moving = false;
                this.moveTimeline.pause();
                this.playerView.stop();
            }
        });

        // initializing the move timeline
        this.moveTimeline = new Timeline(new KeyFrame(Duration.millis(this.moveTime), event -> {GameCtrl.move();}));
        this.moveTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void move() {
        this.boardView.move();
        this.playerView.move();
    }
    
    public void changeBoardView() {
        // stop player view movement
        this.moveTimeline.pause();
        this.playerView.stop();

        // fade out
        FadeTransition fadeoutGameGroup = new FadeTransition(Duration.seconds(2), this.gameGroup);
        fadeoutGameGroup.setFromValue(0.0);
        fadeoutGameGroup.setToValue(1.0);
        fadeoutGameGroup.setCycleCount(1);
        fadeoutGameGroup.play();

        // update the board view
        this.gameGroup.getChildren().remove(this.boardView.getTerrainImageView());
        this.gameGroup.getChildren().remove(this.playerView.getSkinImageView());
        this.gameGroup.getChildren().remove(this.boardView.getOverImageView());
        this.gameGroup.getChildren().remove(this.dialogPane);
        this.boardView = new BoardView();
        this.gameGroup.getChildren().add(this.boardView.getTerrainImageView());
        this.gameGroup.getChildren().add(this.playerView.getSkinImageView());
        this.gameGroup.getChildren().add(this.boardView.getOverImageView());
        this.gameGroup.getChildren().add(this.dialogPane);
       
        // unlock player view movement
        fadeoutGameGroup.setOnFinished(action -> {this.moving = false;});
    }

    public void showDialogView(ArrayList<String> dialogList) {
        // stop player view movement
        this.moveTimeline.pause();
        this.playerView.stop();

        // show the dialog pane
        this.dialoging = true;
        this.dialogIndex = 0;
        this.dialogList.clear();
        this.dialogList.addAll(dialogList);
        this.dialogPane.setContentText(this.dialogList.get(0));
        this.dialogPane.setVisible(this.dialoging);
    }
}
