package main.java.view;

import javafx.application.Application;
import javafx.stage.Stage;



public class View extends Application {
    public static final int stageRows = 448;
    public static final int stageColumns = 512;
    public static final int tileRows = 32;
    public static final int tileColumns = 32;
    private GameView gameView;
    
    @Override
    public void start(Stage stage) {
        // initializing the game view
        this.gameView = new GameView();

        // setting the stage
        stage.setWidth(View.stageColumns);
        stage.setHeight(View.stageRows);
        stage.setResizable(false);
        stage.setTitle("Newdemo");
        stage.setScene(this.gameView.getScene());
        stage.show();
    }
}