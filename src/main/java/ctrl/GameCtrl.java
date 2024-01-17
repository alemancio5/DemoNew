package main.java.ctrl;

import javafx.scene.input.KeyCode;
import main.java.model.Board;
import main.java.model.Player;
import main.java.view.View;



public class GameCtrl {
    private static Player player;
    private static Board board;
    


    public GameCtrl() {
        // initializing the player
        GameCtrl.player = new Player("Ale", 24, 41, "Blue");       
        
        // initializing the board
        GameCtrl.board = new Board("Nowhere");
    }

    public static Player getPlayer() {
        return GameCtrl.player;
    }

    public static Board getBoard() {
        return GameCtrl.board;
    }

    public static void move() {
        switch (GameCtrl.player.getDirection()) {
            case KeyCode.W:
                if (GameCtrl.board.isWalkable(GameCtrl.player.getRow() - 1, GameCtrl.player.getColumn())) {
                    GameCtrl.player.move();
                    View.gameView.move();
                }
                break;
            case KeyCode.A:
                if (GameCtrl.board.isWalkable(GameCtrl.player.getRow(), GameCtrl.player.getColumn() - 1)) {
                    GameCtrl.player.move();
                    View.gameView.move();
                }
                break;
            case KeyCode.S:
                if (GameCtrl.board.isWalkable(GameCtrl.player.getRow() + 1, GameCtrl.player.getColumn())) {
                    GameCtrl.player.move();
                    View.gameView.move();
                }
                break;
            case KeyCode.D:
                if (GameCtrl.board.isWalkable(GameCtrl.player.getRow(), GameCtrl.player.getColumn() + 1)) {
                    GameCtrl.player.move();
                    View.gameView.move();
                }
                break;
            default:
                break;
        }
    }

    public static void action() {
        if (GameCtrl.board.getTiles()[GameCtrl.player.getRow()][GameCtrl.player.getColumn()].isActionable()) {
            GameCtrl.board.getTiles()[GameCtrl.player.getRow()][GameCtrl.player.getColumn()].action();
        } else {
            switch (GameCtrl.player.getDirection()) {
                case KeyCode.W:
                    if (GameCtrl.board.getTiles()[GameCtrl.player.getRow() - 1][GameCtrl.player.getColumn()].isActionable()) {
                        GameCtrl.board.getTiles()[GameCtrl.player.getRow() - 1][GameCtrl.player.getColumn()].action();
                    }
                    break;
                case KeyCode.A:
                    if (GameCtrl.board.getTiles()[GameCtrl.player.getRow()][GameCtrl.player.getColumn() - 1].isActionable()) {
                        GameCtrl.board.getTiles()[GameCtrl.player.getRow()][GameCtrl.player.getColumn() - 1].action();
                    }
                    break;
                case KeyCode.S:
                    if (GameCtrl.board.getTiles()[GameCtrl.player.getRow() + 1][GameCtrl.player.getColumn()].isActionable()) {
                        GameCtrl.board.getTiles()[GameCtrl.player.getRow() + 1][GameCtrl.player.getColumn()].action();
                    }
                    break;
                case KeyCode.D:
                    if (GameCtrl.board.getTiles()[GameCtrl.player.getRow()][GameCtrl.player.getColumn() + 1].isActionable()) {
                        GameCtrl.board.getTiles()[GameCtrl.player.getRow()][GameCtrl.player.getColumn() + 1].action();
                    }
                    break;
                default:
                    throw new IllegalStateException("Invalid key for board action");
            }
        }
    }

    public static void teleport(String destination, int row, int column) {
        GameCtrl.player.setRow(row);
        GameCtrl.player.setColumn(column);
        GameCtrl.board = new Board(destination);
        View.gameView.changeBoardView();
    }
}
