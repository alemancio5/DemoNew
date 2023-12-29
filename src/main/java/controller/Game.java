package main.java.controller;

import javafx.scene.input.KeyCode;
import main.java.model.Board;
import main.java.model.Player;
import main.java.view.View;



public class Game {
    public static Player player;
    public static Board board;
    


    public Game() {
        // initializing the player
        Game.player = new Player("Ale", 15, 15, "Blue");       
        
        // initializing the board
        Game.board = new Board("Nowhere");
    }

    public static void movePlayer(KeyCode key) {
        switch (key) {
            case KeyCode.W:
                if (Game.board.isWalkable(Game.player.getRow() - 1, Game.player.getColumn())) {
                    Game.player.move(key);
                    View.gameView.move(key);
                }
                break;
            case KeyCode.A:
                if (Game.board.isWalkable(Game.player.getRow(), Game.player.getColumn() - 1)) {
                    Game.player.move(key);
                    View.gameView.move(key);
                }
                break;
            case KeyCode.S:
                if (Game.board.isWalkable(Game.player.getRow() + 1, Game.player.getColumn())) {
                    Game.player.move(key);
                    View.gameView.move(key);
                }
                break;
            case KeyCode.D:
                if (Game.board.isWalkable(Game.player.getRow(), Game.player.getColumn() + 1)) {
                    Game.player.move(key);
                    View.gameView.move(key);
                }
                break;
            default:
                break;
        }
    }

    public static void actionBoard(KeyCode key) {
        switch (key) {
            case KeyCode.W:
                Game.board.getTiles()[Game.player.getRow() - 1][Game.player.getColumn()].action();
                break;
            case KeyCode.A:
                Game.board.getTiles()[Game.player.getRow()][Game.player.getColumn() - 1].action();
                break;
            case KeyCode.S:
                Game.board.getTiles()[Game.player.getRow() + 1][Game.player.getColumn()].action();
                break;
            case KeyCode.D:
                Game.board.getTiles()[Game.player.getRow()][Game.player.getColumn() + 1].action();
                break;
            default:
                throw new IllegalStateException("Invalid key for board action");
        }
    }
}
