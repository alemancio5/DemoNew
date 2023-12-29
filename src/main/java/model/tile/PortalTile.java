package main.java.model.tile;

import main.java.controller.Game;
import main.java.model.Board;
import main.java.view.View;

public class PortalTile extends Tile {
    private String destination;
    private int row;
    private int column;

    
    public PortalTile(String boardname, int i, int j) {
        switch (boardname) {
            case "Nowhere":
                if (i == 17 && j == 26) {
                    this.destination = "WoodHouse1";
                    this.row = 7;
                    this.column = 2;
                }
                break;
            case "WoodHouse1":
                if (i == 8 && j == 2) {
                    this.destination = "Nowhere";
                    this.row = 18;
                    this.column = 26;
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal boardname passed to portal tile");
        }

    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public void action() {
        Game.player.setRow(row);
        Game.player.setColumn(column);
        Game.board = new Board(destination);
        View.gameView.refreshBoardView();
        View.gameView.refreshPlayerView();
        View.gameView.refreshOverView();
    }
}