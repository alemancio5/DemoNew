package main.java.view;

import java.io.FileNotFoundException;
import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.java.model.Player;
import main.java.model.Board;



public class View extends Application {
    static final double STAGE_ROWS = 448;
    static final double STAGE_COLUMNS = 512;
    static final double TILE_ROWS = 32;
    static final double TILE_COLUMNS = 32;
    private Player player;
    private Board board;
    private Group group;
    private Scene scene;
    private ImageView playerView;
    private ImageView boardView;

    @Override
    public void start(Stage stage) {
        // initializing the model
        this.player = new Player("ale", 1, 1, "player");
        try {
            this.board = new Board("test");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
        // initializing the view
        this.group = new Group();
        this.scene = new Scene(this.group);
        this.addBoard();
        this.addPlayer();

        // setting the stage
        stage.setWidth(STAGE_COLUMNS);
        stage.setHeight(STAGE_ROWS);
        stage.setResizable(false);
        stage.setTitle("Ciao");
        stage.setScene(this.scene);
        stage.show();
    }

    public void addBoard() {
        Image boardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/board_images/" + board.getBoardname() + ".png")));
        this.boardView = new ImageView(boardImage);
        this.boardView.setX((STAGE_COLUMNS / 2) - (this.player.getColumn() * TILE_COLUMNS) - (TILE_COLUMNS / 2));
        this.boardView.setY((STAGE_ROWS / 2) - (this.player.getRow() * TILE_ROWS) - (TILE_ROWS / 2));
        this.group.getChildren().add(this.boardView);
    }

    public void addPlayer() {
        Image playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/resources/skin_images/" + player.getSkinname() + ".png")));
        this.playerView = new ImageView(playerImage);
        this.playerView.setX((STAGE_COLUMNS / 2) - (TILE_COLUMNS / 2));
        this.playerView.setY((STAGE_ROWS / 2) - (3 * TILE_ROWS / 2));
        this.group.getChildren().add(this.playerView);
    }
}