package main.java.controller;

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

    public static void movePlayer(char direction) {
        switch (direction) {
            case 'W':
                if (Game.board.isWalkable(Game.player.getRow() - 1, Game.player.getColumn())) {
                    Game.player.move(direction);
                    View.gameView.move(direction);
                }
                break;
            case 'A':
                if (Game.board.isWalkable(Game.player.getRow(), Game.player.getColumn() - 1)) {
                    Game.player.move(direction);
                    View.gameView.move(direction);
                }
                break;
            case 'S':
                if (Game.board.isWalkable(Game.player.getRow() + 1, Game.player.getColumn())) {
                    Game.player.move(direction);
                    View.gameView.move(direction);
                }
                break;
            case 'D':
                if (Game.board.isWalkable(Game.player.getRow(), Game.player.getColumn() + 1)) {
                    Game.player.move(direction);
                    View.gameView.move(direction);
                }
                break;
            default:
                break;
        }
    }
}
