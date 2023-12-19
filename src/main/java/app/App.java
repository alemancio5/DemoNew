package main.java.app;

import javafx.application.Application;
import main.java.model.Board;
import main.java.model.Player;
import main.java.view.View;



public class App {
    public static Player player;
    public static Board board;

    public static void main(String[] args) {
        // initializing the player
        App.player = new Player("Ale", 10, 15, "Blue");

        // initializing the board
        App.board = new Board("Beghouse");

        // launching the view
        Application.launch(View.class);
    }
}