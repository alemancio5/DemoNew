package main.java.app;

import javafx.application.Application;
import main.java.ctrl.GameCtrl;
import main.java.view.View;



public class App {
    public static GameCtrl gameCtrl;


    
    public static void main(String[] args) {
        // initializing the game
        App.gameCtrl = new GameCtrl();

        // launching the view
        Application.launch(View.class);
    }
}