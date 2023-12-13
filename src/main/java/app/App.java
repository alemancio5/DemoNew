package main.java.app;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import javafx.application.Application;
import main.java.model.Board;
import main.java.model.Player;
import main.java.view.View;



public class App {
    public static Logger logger = Logger.getLogger("App");
    public static Player player;
    public static Board board;

    public static void main(String[] args) {
        // initializing the player
        App.player = new Player("ale", 10, 15, "player");

        // initializing the board
        try {
            App.board = new Board("test");
        } catch (FileNotFoundException e) {
            App.logger.severe("Board file not found");
        }

        // launching the view
        Application.launch(View.class);
    }
}