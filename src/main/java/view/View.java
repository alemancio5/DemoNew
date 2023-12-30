package main.java.view;

import javafx.application.Application;
import javafx.stage.Stage;



public class View extends Application {
    protected static final int stageRows = 448;
    protected static final int stageColumns = 512;
    protected static final int tileRows = 32;
    protected static final int tileColumns = 32;
    
    public static GameView gameView;


    
    @Override
    public void start(Stage stage) {
        // initializing the game view
        View.gameView = new GameView();

        // setting the stage
        stage.setWidth(View.stageColumns);
        stage.setHeight(View.stageRows);
        stage.setResizable(false);
        stage.setTitle("Newdemo");
        stage.setScene(View.gameView.getScene());
        stage.show();
    }
}