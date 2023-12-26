package main.java.app;

import javafx.application.Application;
import main.java.controller.Game;
import main.java.view.View;



public class App {
    public static Game game;


    
    public static void main(String[] args) {
        // initializing the game
        App.game = new Game();

        // launching the view
        Application.launch(View.class);
    }
}